package servlet;

import bean.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

public class ForeServlet extends BaseForeServlet {

    private static final String SUCCESSMSG = "successmsg";
    private static final String ERRORMSG = "errormsg";

    public String home(HttpServletRequest request, HttpServletResponse response) {
        List<Category> categories = categoryDAO.list();
        for (Category c: categories) {
            c.setSubCategories(subCategoryDAO.listByCategory(c.getId()));
        }
        request.setAttribute("categories", categories);

        Map<String, List<Product>> newProductsByCategory = new HashMap<String, List<Product>>();
        for (Category c: categories) {
            if (!productDAO.listByCategory(c.getId()).isEmpty()) {
                newProductsByCategory.put(c.getName(), productDAO.listNewArrivalsByCategory(c.getId(), 3));
            }
//            System.out.println(c.getName());
        }
        request.setAttribute("newProductsByCategory", newProductsByCategory);

        return "home.jsp";
    }

    public String register(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        if (customerDAO.isExist(username)) {
            request.setAttribute(ERRORMSG, "The username is exist");
            return "register.jsp";
        }

        Customer customer = new Customer();
        customer.setUsername(username);
        customer.setPassword(password);
        customer.setEmail(email);
        customerDAO.add(customer);

        return "@registerSuccess.jsp";
    }

    public String login(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Customer customer = customerDAO.get(username, password);

        if ("admin".equals(username) && "admin".equals(password)) {
            return "admin_home.jsp";
        }

        if (customer == null) {
            request.setAttribute(ERRORMSG, "Wrong username or password");
            return "login.jsp";
        }

        request.getSession().setAttribute("customer", customer);
        return "@forehome";
    }

    public String logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute("customer");
        request.getSession().removeAttribute("cartitems");
        return "@forehome";
    }

    public String modifyProfile(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        int mobile = request.getParameter("mobile") != null ? Integer.parseInt(request.getParameter("mobile").replaceAll("\\p{C}", "")) : null;
        String address = request.getParameter("address");

        Customer customer = customerDAO.get(id);
        customer.setUsername(username);
        customer.setPassword(password);
        customer.setEmail(email);
        customer.setFirstname(firstname);
        customer.setLastname(lastname);
        customer.setMobile(mobile);
        customer.setAddress(address);

        if (customerDAO.update(customer)) {
            request.setAttribute(SUCCESSMSG, "Profile successfully modified");
        } else {
            request.setAttribute(ERRORMSG, "Failed in modify profile");
        }

        request.getSession().setAttribute("customer", customer);

        return "profile.jsp";
    }

    public String modifyBalance(HttpServletRequest request, HttpServletResponse response) {
        Customer customer = (Customer) request.getSession().getAttribute("customer");
        int balance = request.getParameter("balance") != null ? Integer.parseInt(request.getParameter("balance")) : 0;
        customer.setBalance(customer.getBalance() + balance);

        if (customerDAO.updateBalance(customer)) {
            request.setAttribute(SUCCESSMSG, "Balance succesfully charged");
        } else {
            request.setAttribute(ERRORMSG, "Something went wrong");
        }

        request.getSession().setAttribute("customer", customer);

        return "balance.jsp";
    }

    public String product(HttpServletRequest request, HttpServletResponse response) {
        int pid = Integer.parseInt(request.getParameter("pid"));
        Product product = productDAO.get(pid);

        List<Review> reviews = reviewDAO.listByProduct(product.getId());

        request.setAttribute("product", product);
        request.setAttribute("reviews", reviews);

        return "product.jsp";
    }

    public String category(HttpServletRequest request, HttpServletResponse response) {
        int cid = Integer.parseInt(request.getParameter("cid"));
        List<Product> products = productDAO.listByCategory(cid);

        request.setAttribute("products", products);
        request.setAttribute("categoryname", categoryDAO.get(cid).getName());

        return "category.jsp";
    }

    public String subCategory(HttpServletRequest request, HttpServletResponse response) {
        int subcid = Integer.parseInt(request.getParameter("subcid"));
        List<Product> products = productDAO.listBySubcategory(subcid);

        request.setAttribute("products", products);
        request.setAttribute("categoryname", subCategoryDAO.get(subcid).getName());

        return "category.jsp";
    }

    public String search(HttpServletRequest request, HttpServletResponse response) {
        String keyword = request.getParameter("keyword");
        List<Product> products = productDAO.search(keyword);

        request.setAttribute("products", products);
        request.setAttribute("keyword", keyword);

        return "searchResult.jsp";
    }

    public String addToCart(HttpServletRequest request, HttpServletResponse response) {
        int pid = Integer.parseInt(request.getParameter("pid"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        Product product = productDAO.get(pid);

        List<Orderitem> cartitems = (List<Orderitem>) request.getSession().getAttribute("cartitems");


        boolean isExist = false;
        if (cartitems == null) {
            cartitems = new ArrayList<>();
        } else {

            for (Orderitem orderitem: cartitems) {
                if (orderitem.getProduct().getId() == pid) {
                    orderitem.setPquantity(orderitem.getPquantity() + quantity);
                    orderitem.setSubtotal(orderitem.getPquantity() * product.getPrice());
                    isExist = true;
                    break;
                }
            }
        }

        if (!isExist) {
            Orderitem orderitem = new Orderitem();
            orderitem.setProduct(product);
            orderitem.setPquantity(quantity);
            orderitem.setSubtotal(product.getPrice() * quantity);
            cartitems.add(orderitem);
        }

        int totalprice = 0;
        int cartTotalItemNumber = 0;
        for (Orderitem orderitem: cartitems) {
            totalprice += orderitem.getSubtotal();
            cartTotalItemNumber += orderitem.getPquantity();
        }

        request.setAttribute("cartTotalItemNumber", cartTotalItemNumber);
        request.getSession().setAttribute("cartitems", cartitems);
        request.getSession().setAttribute("totalprice", totalprice);

        return "cart.jsp";
    }

    public String changeCartitem(HttpServletRequest request, HttpServletResponse response) {
        int pid = Integer.parseInt(request.getParameter("pid"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        List<Orderitem> cartitems = (List<Orderitem>) request.getSession().getAttribute("cartitems");

        for (Orderitem orderitem: cartitems) {
            if (orderitem.getProduct().getId() == pid) {
                orderitem.setPquantity(quantity);
                orderitem.setSubtotal(quantity * orderitem.getProduct().getPrice());
                break;
            }
        }

        int totalprice = 0;
        int cartTotalItemNumber = 0;
        for (Orderitem orderitem: cartitems) {
            totalprice += orderitem.getSubtotal();
            cartTotalItemNumber += orderitem.getPquantity();
        }

        request.setAttribute("cartTotalItemNumber", cartTotalItemNumber);
        request.getSession().setAttribute("cartitems", cartitems);
        request.getSession().setAttribute("totalprice", totalprice);

        return "cart.jsp";
    }

    public String deleteCartitem(HttpServletRequest request, HttpServletResponse response) {
        int pid = Integer.parseInt(request.getParameter("pid"));
        List<Orderitem> cartitems = (List<Orderitem>) request.getSession().getAttribute("cartitems");


        for (Orderitem orderitem: cartitems) {
            if (orderitem.getProduct().getId() == pid) {
                cartitems.remove(orderitem);
                break;
            }
        }

        int totalprice = 0;
        int cartTotalItemNumber = 0;
        for (Orderitem orderitem: cartitems) {
            totalprice += orderitem.getSubtotal();
            cartTotalItemNumber += orderitem.getPquantity();
        }

        request.setAttribute("cartTotalItemNumber", cartTotalItemNumber);
        request.getSession().setAttribute("cartitems", cartitems);
        request.getSession().setAttribute("totalprice", totalprice);

        return "cart.jsp";
    }

    public String checkoutCart(HttpServletRequest request, HttpServletResponse response) {
        Customer customer = (Customer) request.getSession().getAttribute("customer");
        List<Orderitem> cartitems = (List<Orderitem>) request.getSession().getAttribute("cartitems");

        if (customer == null) {
            request.setAttribute("errormsg", "Please login");
            return "login.jsp";
        }

        int totalprice = 0;
        for (Orderitem orderitem: cartitems) {
            totalprice += orderitem.getSubtotal();
        }

        List<Shipmethod> shipmethods = shipmethodDAO.list();

        request.setAttribute("shipmethods", shipmethods);
        request.getSession().setAttribute("totalprice", totalprice);

        return "placeOrder.jsp";
    }

    public String placeOrder(HttpServletRequest request, HttpServletResponse response) {
        List<Orderitem> cartitems = (List<Orderitem>) request.getSession().getAttribute("cartitems");
        request.getSession().removeAttribute("cartitems");
        int shipmethodid = Integer.parseInt(request.getParameter("shipmethodid"));

        int temptotal = 0;
        for (Orderitem orderitem: cartitems) temptotal += orderitem.getSubtotal();
        temptotal += shipmethodDAO.get(shipmethodid).getCost();

        Customer customer = (Customer) request.getSession().getAttribute("customer");
        customer.setFirstname(request.getParameter("firstname"));
        customer.setLastname(request.getParameter("lastname"));
        customer.setEmail(request.getParameter("email"));
        customer.setAddress(request.getParameter("address"));
        customerDAO.update(customer);


        String ordernum = UUID.randomUUID().toString();
        Order order = new Order();
        order.setNum(ordernum);
        order.setPcount(cartitems.size());
        order.setOrderdate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        order.setStatus(Order.STATUS_AWAITING_PAYMENT);
        order.setShipmethod(shipmethodDAO.get(shipmethodid));
        order.setCustid(customer.getId());
        order.setTotalprice(temptotal);

        order.setOrderitems(cartitems);

        customer.getOrders().add(order);

        orderDAO.add(order);
        for (Orderitem orderitem: cartitems) {
            orderitem.setOrdernum(ordernum);
            orderitemsDAO.add(orderitem);
        }

        customer.setOrders(orderDAO.get(customer.getId()));

        request.getSession().removeAttribute("cartitems");
        request.setAttribute("cartTotalItemNumber", 0);
        request.setAttribute(SUCCESSMSG, "You successfully placed an order!");

        return "orders.jsp";
    }

    public String payOrder(HttpServletRequest request, HttpServletResponse response) {
        String num = request.getParameter("num");
        Order order = orderDAO.get(num);

        request.setAttribute("order", order);
        return "payOrder.jsp";
    }

    public String confirmOrder(HttpServletRequest request, HttpServletResponse response) {
        String ordernum = request.getParameter("ordernum");
        Customer customer = (Customer) request.getSession().getAttribute("customer");

        Order order = null;
        for (Order o: customer.getOrders()) {
            if (o.getNum().equals(ordernum)) {
                customer.getOrders().remove(o);
                order = o;
                break;
            }
        }

        if (order.getTotalprice() > customer.getBalance()) {
            customer.getOrders().add(order);
            request.getSession().setAttribute("customer", customer);
            request.setAttribute(ERRORMSG, "Your current balance is less than total price. Please charge your balance!");
            return "orders.jsp";
        }

        customer.setBalance(customer.getBalance() - order.getTotalprice());
        customerDAO.updateBalance(customer);

        order.setStatus(Order.STATUS_ORDER_CONFRIMED);
        orderDAO.udpateStatus(order);

        customer.setOrders(orderDAO.get(customer.getId()));
        request.getSession().setAttribute("customer", customer);

        return "orders.jsp";
    }

    public String viewInvoice(HttpServletRequest request, HttpServletResponse response) {
        String ordernum = request.getParameter("ordernum");
        Customer customer = (Customer) request.getSession().getAttribute("customer");

        Order order = null;
        for (Order o: customer.getOrders()) {
            if (o.getNum().equals(ordernum)) {
                order = o;
                break;
            }
        }

        request.setAttribute("order", order);

        return "invoice.jsp";
    }

    // TODO
    public String deleteOrder(HttpServletRequest request, HttpServletResponse response) {


        return "";
    }

    public String newReview(HttpServletRequest request, HttpServletResponse response) {
        String content = request.getParameter("content");
        String custuname = request.getParameter("custuname");
        int pid = Integer.parseInt(request.getParameter("pid"));
        Product product = productDAO.get(pid);
        int rate = request.getParameter("rate") != null ? Integer.parseInt(request.getParameter("rate")) : 0;

        Review r = new Review();
        r.setContent(content);
        r.setCustuname(custuname);
        r.setPid(pid);
        r.setRate(rate);

        reviewDAO.add(r);

//        product.getReviews().add(r);
//
//        request.setAttribute("product", product);

        return "@foreproduct?pid=" + pid;
    }

}
