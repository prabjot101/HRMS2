package com.example.ems.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class WebController {

    @Autowired
    private UserServices userServices;
    private UsersDao repo;


    @GetMapping("/")
    public String hom() {

        return "home";
    }

    @GetMapping("/pricing")
    public String pricing(Model model)
    {
        List<Users> employee = userServices.findAll();
        //employee = userServices.findAll();
        model.addAttribute("Employees", employee );
        return "pricing";
    }

    ///////HighChart API
    @GetMapping("/dashboard")
    public String dash() {

        return "dashboard";
    }

    @GetMapping("/barchart")
    public String getAllEmployee()
    {
//        List<String> nameList = userServices.getUsers().stream().map(x->x.getName()).collect(Collectors.toList());
//        List<String> AgeList = userServices.getUsers().stream().map(x->x.getAge()).collect(Collectors.toList());
//        model.addAttribute("name", nameList );
//        model.addAttribute("age", AgeList);
//        System.out.println(AgeList.get(2));
        return "barchart";
    }

    @GetMapping("/scatter")
    public String getAllEmploy()
    {
//        List<String> nameList = userServices.getUsers().stream().map(x->x.getSalary()).collect(Collectors.toList());
//        List<String> AgeList = userServices.getUsers().stream().map(x->x.getAge()).collect(Collectors.toList());
//        model.addAttribute("salary", nameList );
//        model.addAttribute("age", AgeList);
//        System.out.println(AgeList.get(2));
        return "scatter";
    }

    @GetMapping("/piechart")
    public String getAllEmploye()
    {

        return "piechart";
    }

    @GetMapping("/crud")
    public String Crud(Model model,  @RequestParam(name="keyword",defaultValue = "")
            String keyword)
    {
        List<Users> employee;
        if (keyword.isEmpty()) {
            employee = userServices.findAll();
            model.addAttribute("Employees", employee );
            return "crud";
        } else {

            long key = Long.parseLong(keyword);
            employee = userServices.findByID(key);


        }


//        List<Users> employees = userServices.getUsers();
        model.addAttribute("Employees", employee );
        return "crud";
    }

    @PostMapping("/register")
     public String register(@ModelAttribute Users u)
     {
         System.out.println(u);
         userServices.addUser(u);
         userServices.findAll();
         return "crud";

     }

     @GetMapping("/delete")
     public String delete(Long id){
         userServices.deleteUser(id);
         return "redirect:/crud";
     }


     ///Genrate Report





    @GetMapping("/DownloadCsv")
    public void exportToCSV(HttpServletResponse response) throws IOException
    {
        response.setContentType("text/csv");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=TransactionReport_" + currentDateTime + ".csv";
        response.setHeader(headerKey, headerValue);

        List<Users> listUsers = userServices.getUsers();

        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = {"Name", "Email", "city", "country", "Phone", "image", "fileName", "message", "Age", "position", "Salary"};
        String[] nameMapping = {"Name", "Email", "city", "country", "Phone", "image", "fileName", "message", "Age", "position", "Salary"};

        csvWriter.writeHeader(csvHeader);

        for (Users user : listUsers) {
            csvWriter.write(user, nameMapping);
        }

        csvWriter.close();
        System.out.println("CSV Downloaded Successfully");


    }








}
