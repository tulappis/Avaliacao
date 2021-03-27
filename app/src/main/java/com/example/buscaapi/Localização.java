package com.example.buscaapi;

public class Localização {
     private String country_code;
     private String country_name;
     private String region_code;
     private String region_name;
     private String city;

    public void Localizacao(String ccode, String cname, String rcode, String rname, String city) {
        this.country_code = ccode;
        this.country_name = cname;
        this.region_code = rcode;
        this.region_name = rname;
        this.city = city;
    }

    public Localização(String ccode, String cname, String rcode, String rname, String city) {
    }

    public String getCountry_code(){return this.country_code;}
    public String getCountry_name(){return this.country_name;}
    public String getRegion_code(){return this.region_code;}
    public String getRegion_name(){return this.region_name;}
    public String getCity(){return this.city;}
}
