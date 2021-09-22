package bean;

import java.sql.Date;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

public class Order {
    public static final String STATUS_AWAITING_PAYMENT = "awaiting payment";
    public static final String STATUS_ORDER_CONFRIMED = "order confirmed";
    public static final String STATUS_COMPLETE = "complete";
    public static final String STATUS_CANCELLED = "cancelled";

    private String num;
    private int pcount;
    private Date orderdate;
    private Date paydate;
    private Date shipdate;
    private String status;
    private int totalprice;
    private Shipmethod shipmethod; // shipmethodid
    private int custid;
    private Customer customer;

    private long remainingDay;

    public long getRemainingDay() {
        long days = -1;
        if (paydate != null) {
            LocalDate dateDB = paydate.toLocalDate();
            LocalDate dateSS = LocalDate.now();
            days = 5 - Duration.between(dateDB.atStartOfDay(), dateSS.atStartOfDay()).toDays();
        }

        return days;
    }

    public void setRemainingDay(long remainingDay) {
        long days = -1;
        if (paydate != null) {
            LocalDate dateDB = paydate.toLocalDate();
            LocalDate dateSS = LocalDate.now();
            days = Duration.between(dateDB.atStartOfDay(), dateSS.atStartOfDay()).toDays();
        }
        this.remainingDay = days;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    private List<Orderitem> orderitems;

    public List<Orderitem> getOrderitems() {
        return orderitems;
    }

    public void setOrderitems(List<Orderitem> orderitems) {
        this.orderitems = orderitems;
    }

    public Shipmethod getShipmethod() {
        return shipmethod;
    }

    public void setShipmethod(Shipmethod shipmethod) {
        this.shipmethod = shipmethod;
    }

    public int getCustid() {
        return custid;
    }

    public void setCustid(int custid) {
        this.custid = custid;
    }

    public int getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(int totalprice) {
        this.totalprice = totalprice;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public int getPcount() {
        return pcount;
    }

    public void setPcount(int pcount) {
        this.pcount = pcount;
    }

    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    public Date getPaydate() {
        return paydate;
    }

    public void setPaydate(Date paydate) {
        this.paydate = paydate;
    }

    public Date getShipdate() {
        return shipdate;
    }

    public void setShipdate(Date shipdate) {
        this.shipdate = shipdate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
