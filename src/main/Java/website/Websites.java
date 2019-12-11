package website;

import paperToHTML.ResultsList;
import searchDB.ChartMaker;
import Papers.*;
import searchDB.SearchDB;

import java.sql.SQLException;
import java.util.HashMap;

public class Websites {
    private HashMap<String, String> websites;

    public Websites (){
        String index = "<html class=\"h-100\">\n" +
                "  <head>\n" +
                "    <meta charset=\"utf-8\" />\n" +
                "    <meta\n" +
                "      name=\"viewport\"\n" +
                "      content=\"width=device-width, initial-scale=1.0, shrink-to-fit=no\"\n" +
                "    />\n" +
                "    <meta name=\"description\" content=\"\" />\n" +
                "    <meta name=\"author\" content=\"\" />\n" +
                "    <title>Experimental DB</title>\n" +
                "    <link\n" +
                "      rel=\"stylesheet\"\n" +
                "      href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.4.0/css/bootstrap.min.css\"\n" +
                "      integrity=\"sha384-SI27wrMjH3ZZ89r4o+fGIJtnzkAnFs3E4qz9DIYioCQ5l9Rd/7UAa8DHcaL8jkWt\"\n" +
                "      crossorigin=\"anonymous\"\n" +
                "    />\n" +
                "    <link rel=\"stylesheet\" href=\"/ExDB/style.css\">\n" +
                "  </head>\n" +
                "\n" +
                "  <body class=\"h-100\">\n" +
                "    <!-- Navbar code -->\n" +
                "    <nav class=\"navbar navbar-dark bg-dark\">\n" +
                "        <a class=\"navbar-brand\" href=\"/ExDB/\">ExDB</a>\n" +
                "        <form class=\"form-inline btn-toolbar\">\n" +
                "          <div class=\"btn-toolbar\">\n" +
                "            <a\n" +
                "              class=\"btn btn-success my-2 mx-2 my-sm-0\"\n" +
                "              href=\"/ExDB/signin\"\n" +
                "              role=\"button\"\n" +
                "              >Sign in</a\n" +
                "            >\n" +
                "            <a\n" +
                "              class=\"btn btn-success my-2 mx-2 my-sm-0\"\n" +
                "              href=\"/ExDB/register\"\n" +
                "              role=\"button\"\n" +
                "              >Register</a\n" +
                "            >\n" +
                "          </div>\n" +
                "        </form>\n" +
                "      </nav>\n" +
                "      \n" +
                "    <div class=\"container h-100\">\n" +
                "      <div class=\"row h-100 justify-content-center align-items-center\">\n" +
                "        <div class=\"col-sm-12 col-md-8 col-lg-6 mx-auto\">\n" +
                "          <div class=\"card text-center d-flex my-auto\">\n" +
                "            <div class=\"card-body\">\n" +
                "              <!-- Card for search utility -->\n" +
                "              <h2 class=\"card-title mb-3\">Experimental database</h2>\n" +
                "              <form action=\"/ExDB/results\" method=\"get\">\n" +
                "                <div class=\"form-row\">\n" +
                "                  <div class=\"form-group col px-auto mt-2 mb-3\">\n" +
                "                    <input name=\"search\" class=\"form-control\" type=\"search\" placeholder=\"What are you looking for?\" aria-label=\"Search\">\n" +
                "                  </div>\n" +
                "                  \n" +
                "                  <div class=\"form-group mx-1 px-auto mt-2 mb-3\">\n" +
                "                    <button class=\"btn btn-success\" type=\"submit\" role=\"button\">Search</button>\n" +
                "                  </div>\n" +
                "                </div>\n" +
                "\n" +
                "                <div class=\"form-row\">\n" +
                "                  <div class=\"col px-auto\">\n" +
                "                    <label for=\"exampleFormControlSelect1\">\n" +
                "                      In vivo/In vitro</label\n" +
                "                    >\n" +
                "                    <select class=\"form-control\" id=\"exampleFormControlSelect1\" name=\"filter1\">\n" +
                "                      <option selected>il8</option>\n" +
                "                      <option>il12</option>\n" +
                "                      <option>nphil</option>\n" +
                "                      <option>tnfavivo</option>\n" +
                "                    </select>\n" +
                "                  </div>\n" +
                "\n" +
                "                  <div class=\"col px-auto\">\n" +
                "                    <label for=\"exampleFormControlSelect2\">Live/Fixed</label>\n" +
                "                    <select class=\"form-control\" id=\"exampleFormControlSelect2\" name=\"filter2\">\n" +
                "                      <option selected> - </option>\n" +
                "                      <option>Live</option>\n" +
                "                      <option>Fixed</option>\n" +
                "                    </select>\n" +
                "                  </div>\n" +
                "                </div>\n" +
                "                <div class=\"form-row\">\n" +
                "                  <div class=\"col\"></div>\n" +
                "                  <div class=\"col\"></div>\n" +
                "                </div>\n" +
                "              </form>\n" +
                "            </div>\n" +
                "          </div>\n" +
                "        </div>\n" +
                "      </div>\n" +
                "    </div>\n" +
                "  </body>\n" +
                "</html>";
        websites = new HashMap<String, String>();
        websites.put("index", index);
        websites.put("results", new FileToString("results.html").toString());
        websites.put("register", new FileToString("register.html").toString());
        websites.put("signin", new FileToString("signin.html").toString());
        websites.put("stylesheets_common", new FileToString("stylesheets/common.css").toString());
        websites.put("scripts_resultsChart", new FileToString("scripts/resultsChart.js").toString());
    }

    public String get(String website){
        return websites.get(website);
    }

    public String getSearch(String SearchBar, String Filter1, String Filter2) throws SQLException {
        SearchDB search = new SearchDB();
        ResultsList cardResults = new ResultsList(search.Searchdb("id=3;", "il8"));
        ChartMaker chart = new ChartMaker(search.Searchdb("id=3;", "il8"));
        String chartBuilder = "";
        chartBuilder = websites.get("scripts_resultsChart").replace("TIMELABELS", chart.getLabels());
        chartBuilder = chartBuilder.replace("DATASETS", chart.getDatasets());
        websites.put("scripts_resultsChart", chartBuilder);

        String resultsBuilder = websites.get("results").replace("RESULTS", cardResults.toString());
        return resultsBuilder;
    }
}
