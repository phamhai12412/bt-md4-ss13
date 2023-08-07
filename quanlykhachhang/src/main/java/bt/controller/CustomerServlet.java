package bt.controller;

import bt.model.Customer;
import bt.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@WebServlet(name = "Customers", value = "/Customers")
public class CustomerServlet extends HttpServlet {
CustomerService customerService;

    public void init() {
customerService=new CustomerService();
    }


public void showlist(HttpServletRequest request, HttpServletResponse response, List<Customer> list) throws IOException, ServletException{
        request.setAttribute("customers",list);
        request.getRequestDispatcher("/WEB-INF/list.jsp").forward(request,response);
}
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
request.setCharacterEncoding("UTF-8");
        String action=request.getParameter("action");
if(action==null){
    showlist(request,response,customerService.getall());
} else {
    switch (action){
        case "DELETE":
            int id= Integer.parseInt(request.getParameter("id"));
            customerService.delete(id);
            break;
        case "EDIT":
            int eid= Integer.parseInt(request.getParameter("id"));
            Customer c=customerService.finbyid(eid);
            request.setAttribute("customer",c);
            request.getRequestDispatcher("/WEB-INF/edit.jsp").forward(request,response);
break;
        case "CREATE":
            request.getRequestDispatcher("/WEB-INF/add.jsp").forward(request,response);
 break;
        case "SEARCH":
            String seachname=request.getParameter("search-name");
            String by=request.getParameter("by");
            String sort=request.getParameter("sort");
            List<Customer> list=seach(seachname,by,sort);
            request.setAttribute("search-name",seachname);
            request.setAttribute("by",by);
            request.setAttribute("sort",sort);
            showlist(request,response,list);
    }
    showlist(request,response,customerService.getall());
}
    }
public List<Customer> seach(String name, String by  ,String sort ){

        List<Customer> customerList=customerService.getall();
        List<Customer> listseach=new ArrayList<>();
    for (Customer c: customerList
         ) {
        if(c.getName().toLowerCase().contains(name.toLowerCase())){
            listseach.add(c);
        }
    }
    switch (sort){
        case "name":
            if(by.equalsIgnoreCase("ASC")){
                listseach.sort(Comparator.comparing(Customer::getName));
            }else {
                listseach.sort(Comparator.comparing(Customer::getName).reversed());

            }
        case "address":
            if(by.equalsIgnoreCase("ASC")){
                listseach.sort(Comparator.comparing(Customer::getName));
            }else {
                listseach.sort(Comparator.comparing(Customer::getName).reversed());

            }
        case "email":
            if(by.equalsIgnoreCase("ASC")){
                listseach.sort(Comparator.comparing(Customer::getName));
            }else {
                listseach.sort(Comparator.comparing(Customer::getName).reversed());

            }
    } return listseach;
}
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
String action=request.getParameter("action");
switch (action){
    case "UPDATE":
        String ename=request.getParameter("name");
        String eemail=request.getParameter("email");
        String eaddress=request.getParameter("address");
        String ephone=request.getParameter("phone");
        boolean esex= Boolean.parseBoolean(request.getParameter("sex"));
        int eage= Integer.parseInt(request.getParameter("age"));
        int id= Integer.parseInt(request.getParameter("id"));
  customerService.save(new Customer( id,ename,eemail,eaddress,ephone,esex,eage));
  break;
    case "ADD":
        String name=request.getParameter("name");
        String email=request.getParameter("email");
        String address=request.getParameter("address");
        String phone=request.getParameter("phone");
        boolean sex= Boolean.parseBoolean(request.getParameter("sex"));
        int age= Integer.parseInt(request.getParameter("age"));

        customerService.save(new Customer( 0,name,email,address,phone,sex,age));
}
        showlist(request,response,customerService.getall());
}


    public void destroy() {
    }
}