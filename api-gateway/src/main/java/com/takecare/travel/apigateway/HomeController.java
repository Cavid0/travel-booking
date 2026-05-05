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
        + "*{box-sizing:border-box}body{margin:0;font-family:Inter,Arial,sans-serif;background:#eef5ff;color:#172033}"
        + "header{background:linear-gradient(135deg,#0f46d9,#00b894);color:white;padding:56px 20px;text-align:center}"
        + "header h1{margin:0 0 12px;font-size:48px}header p{margin:0 auto;font-size:19px;opacity:.95;max-width:780px}"
        + "main{max-width:1120px;margin:30px auto;padding:0 18px}.grid{display:grid;grid-template-columns:repeat(auto-fit,minmax(280px,1fr));gap:18px}"
        + ".card{background:white;border-radius:18px;padding:24px;box-shadow:0 12px 30px rgba(18,38,63,.10)}.card h2{margin-top:0;color:#1769ff}.hero{margin-top:-55px;border:1px solid rgba(255,255,255,.7)}"
        + ".actions{display:flex;gap:10px;flex-wrap:wrap}.btn,button{display:inline-block;border:0;border-radius:12px;padding:12px 16px;background:#1769ff;color:white;cursor:pointer;font-weight:800;text-decoration:none}button:hover,.btn:hover{background:#0d4ec7}.secondary{background:#00a97f}.secondary:hover{background:#008f6d}"
        + "label{display:block;font-weight:700;margin-top:12px}input{width:100%;padding:12px;border:1px solid #d0d7e2;border-radius:12px;margin-top:6px}"
        + "pre{white-space:pre-wrap;word-break:break-word;background:#101828;color:#d1e7ff;padding:18px;border-radius:14px;min-height:170px}.muted{color:#667085}.pill{display:inline-block;background:#e8f1ff;color:#1769ff;border-radius:999px;padding:7px 10px;margin:3px;font-weight:700}"
        + "</style></head><body>"
        + "<header><h1>Travel Booking</h1><p>Real website-style Spring Cloud microservice app. One public Gateway link routes to Flight, Hotel and Car Rental services.</p></header>"
        + "<main><div class=\"card hero\"><h2>One public link</h2><p class=\"muted\">Main site URL after Render deploy: <b>https://travel-booking-gateway.onrender.com</b></p>"
        + "<span class=\"pill\">Eureka Discovery</span><span class=\"pill\">API Gateway</span><span class=\"pill\">Flight Service</span><span class=\"pill\">Hotel Service</span><span class=\"pill\">Car Rental Service</span>"
        + "</div><section class=\"grid\" style=\"margin-top:18px\">"
        + "<div class=\"card\"><h2>✈️ Search Flights</h2><label>Origin</label><input id=\"origin\" value=\"NYC\"><label>Destination</label><input id=\"destination\" value=\"LAX\"><div class=\"actions\" style=\"margin-top:14px\"><button onclick=\"searchFlights()\">Search Flights</button><button class=\"secondary\" onclick=\"loadData('/api/flights')\">All Flights</button></div></div>"
        + "<div class=\"card\"><h2>🏨 Search Hotels</h2><label>Location</label><input id=\"hotelLocation\" value=\"LosAngeles\"><div class=\"actions\" style=\"margin-top:14px\"><button onclick=\"searchHotels()\">Search Hotels</button><button class=\"secondary\" onclick=\"loadData('/api/hotels')\">All Hotels</button></div></div>"
        + "<div class=\"card\"><h2>🚗 Search Cars</h2><label>Location</label><input id=\"carLocation\" value=\"LosAngeles\"><div class=\"actions\" style=\"margin-top:14px\"><button onclick=\"searchCars()\">Search Cars</button><button class=\"secondary\" onclick=\"loadData('/api/cars')\">All Cars</button></div></div>"
        + "</section><h2>Result from Microservices</h2><pre id=\"result\">Choose a search above. The browser calls the API Gateway, and the Gateway routes to the correct microservice.</pre></main>"
        + "<script>async function loadData(path){const box=document.getElementById('result');box.textContent='Loading '+path+' ...';try{const res=await fetch(path);if(!res.ok)throw new Error(res.status+' '+res.statusText);const data=await res.json();box.textContent=JSON.stringify(data,null,2);}catch(e){box.textContent='Could not load data. Deploy all Render services and set Gateway env URLs.\\n\\n'+e;}}function searchFlights(){loadData('/api/flights/search?origin='+encodeURIComponent(origin.value)+'&destination='+encodeURIComponent(destination.value));}function searchHotels(){loadData('/api/hotels/search?location='+encodeURIComponent(hotelLocation.value));}function searchCars(){loadData('/api/cars/search?location='+encodeURIComponent(carLocation.value));}</script>"
        + "</body></html>";
  }
}