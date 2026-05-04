package com.takecare.travel.apigateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
  @GetMapping(value = "/", produces = "text/html")
  public String home() {
    return "<!doctype html>"
        + "<html lang=\"en\"><head>"
        + "<meta charset=\"UTF-8\" />"
        + "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />"
        + "<title>Travel Booking</title>"
        + "<style>"
        + "*{box-sizing:border-box}body{margin:0;font-family:Arial,sans-serif;background:#f3f7fb;color:#172033}"
        + "header{background:linear-gradient(135deg,#1769ff,#00b894);color:white;padding:45px 20px;text-align:center}"
        + "header h1{margin:0 0 10px;font-size:42px}header p{margin:0;font-size:18px;opacity:.95}"
        + "main{max-width:1000px;margin:30px auto;padding:0 18px}.grid{display:grid;grid-template-columns:repeat(auto-fit,minmax(240px,1fr));gap:18px}"
        + ".card{background:white;border-radius:16px;padding:22px;box-shadow:0 10px 25px rgba(0,0,0,.08)}.card h2{margin-top:0;color:#1769ff}"
        + "button{display:inline-block;border:0;border-radius:10px;padding:11px 15px;background:#1769ff;color:white;cursor:pointer;font-weight:bold}button:hover{background:#0d4ec7}"
        + "pre{white-space:pre-wrap;word-break:break-word;background:#101828;color:#d1e7ff;padding:18px;border-radius:14px;min-height:120px}.muted{color:#667085}"
        + "</style></head><body>"
        + "<header><h1>Travel Booking Microservices</h1><p>Flights, hotels and car rental APIs deployed with Docker + CI/CD</p></header>"
        + "<main><section class=\"grid\">"
        + "<div class=\"card\"><h2>Flights</h2><p class=\"muted\">Browse available flights.</p><button onclick=\"loadData('/api/flights')\">Load Flights</button></div>"
        + "<div class=\"card\"><h2>Hotels</h2><p class=\"muted\">Browse available hotels.</p><button onclick=\"loadData('/api/hotels')\">Load Hotels</button></div>"
        + "<div class=\"card\"><h2>Cars</h2><p class=\"muted\">Browse rental cars.</p><button onclick=\"loadData('/api/cars')\">Load Cars</button></div>"
        + "</section><h2>API Result</h2><pre id=\"result\">Click one of the buttons above.</pre></main>"
        + "<script>async function loadData(path){const box=document.getElementById('result');box.textContent='Loading '+path+' ...';try{const res=await fetch(path);const data=await res.json();box.textContent=JSON.stringify(data,null,2);}catch(e){box.textContent='Could not load data. Make sure all Render services are deployed and gateway env variables are set.\\n\\n'+e;}}</script>"
        + "</body></html>";
  }
}