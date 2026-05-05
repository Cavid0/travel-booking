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
        + ".actions{display:flex;gap:10px;flex-wrap:wrap}a.btn,button{display:inline-block;border:0;border-radius:10px;padding:11px 15px;background:#1769ff;color:white;cursor:pointer;font-weight:bold;text-decoration:none}a.btn.secondary{background:#00a97f}button:hover,a.btn:hover{background:#0d4ec7}a.btn.secondary:hover{background:#008f6d}"
        + "pre{white-space:pre-wrap;word-break:break-word;background:#101828;color:#d1e7ff;padding:18px;border-radius:14px;min-height:120px}.muted{color:#667085}"
        + "</style></head><body>"
        + "<header><h1>Travel Booking Microservices</h1><p>All APIs are collected under one Render gateway link</p></header>"
        + "<main><div class=\"card\"><h2>Single public link</h2><p class=\"muted\">Use this gateway as the main app URL: <b>https://travel-booking-gateway.onrender.com</b></p>"
        + "<div class=\"actions\"><a class=\"btn\" href=\"/api/flights\">/api/flights</a><a class=\"btn\" href=\"/api/hotels\">/api/hotels</a><a class=\"btn\" href=\"/api/cars\">/api/cars</a></div></div>"
        + "<section class=\"grid\" style=\"margin-top:18px\">"
        + "<div class=\"card\"><h2>Flights</h2><p class=\"muted\">Browse available flights via gateway.</p><div class=\"actions\"><button onclick=\"loadData('/api/flights')\">Load Flights</button><a class=\"btn secondary\" href=\"/api/flights\">Open API</a></div></div>"
        + "<div class=\"card\"><h2>Hotels</h2><p class=\"muted\">Browse available hotels via gateway.</p><div class=\"actions\"><button onclick=\"loadData('/api/hotels')\">Load Hotels</button><a class=\"btn secondary\" href=\"/api/hotels\">Open API</a></div></div>"
        + "<div class=\"card\"><h2>Cars</h2><p class=\"muted\">Browse rental cars via gateway.</p><div class=\"actions\"><button onclick=\"loadData('/api/cars')\">Load Cars</button><a class=\"btn secondary\" href=\"/api/cars\">Open API</a></div></div>"
        + "</section><h2>API Result</h2><pre id=\"result\">Click one of the buttons above.</pre></main>"
        + "<script>async function loadData(path){const box=document.getElementById('result');box.textContent='Loading '+path+' ...';try{const res=await fetch(path);const data=await res.json();box.textContent=JSON.stringify(data,null,2);}catch(e){box.textContent='Could not load data. Make sure all Render services are deployed and gateway env variables are set.\\n\\n'+e;}}</script>"
        + "</body></html>";
  }
}