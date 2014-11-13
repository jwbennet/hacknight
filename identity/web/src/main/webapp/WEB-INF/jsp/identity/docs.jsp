<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Organizer Identity Module - Swagger UI</title>
    <link href='//fonts.googleapis.com/css?family=Droid+Sans:400,700' rel='stylesheet' type='text/css'/>
    <link href='/css/reset.css' media='screen' rel='stylesheet' type='text/css'/>
    <link href='/css/screen.css' media='screen' rel='stylesheet' type='text/css'/>
    <link href='/css/reset.css' media='print' rel='stylesheet' type='text/css'/>
    <link href='/css/screen.css' media='print' rel='stylesheet' type='text/css'/>
    <script src='/js/shred.bundle.js' type='text/javascript'></script>
    <script src='/js/jquery-1.8.0.min.js' type='text/javascript'></script>
    <script src='/js/jquery.slideto.min.js' type='text/javascript'></script>
    <script src='/js/jquery.wiggle.min.js' type='text/javascript'></script>
    <script src='/js/jquery.ba-bbq.min.js' type='text/javascript'></script>
    <script src='/js/handlebars-1.0.0.js' type='text/javascript'></script>
    <script src='/js/underscore-min.js' type='text/javascript'></script>
    <script src='/js/backbone-min.js' type='text/javascript'></script>
    <script src='/js/swagger.js' type='text/javascript'></script>
    <script src='/js/swagger-client.js' type='text/javascript'></script>
    <script src='/js/swagger-ui.js' type='text/javascript'></script>
    <script src='/js/highlight.7.3.pack.js' type='text/javascript'></script>

    <!-- enabling this will enable oauth2 implicit scope support -->
    <script src='/js/swagger-oauth.js' type='text/javascript'></script>
    <script type="text/javascript">
        $(function () {
            var url = window.location.search.match(/url=([^&]+)/);
            if (url && url.length > 1) {
                url = url[1];
            } else {
                url = "http://localhost:8080/identity/api/api-docs";
            }
            window.swaggerUi = new SwaggerUi({
                url: url,
                dom_id: "swagger-ui-container",
                supportedSubmitMethods: ['get', 'post', 'put', 'delete'],
                onComplete: function(swaggerApi, swaggerUi){
                    log("Loaded SwaggerUI");
                    if(typeof initOAuth == "function") {
                        /*
                         initOAuth({
                         clientId: "your-client-id",
                         realm: "your-realms",
                         appName: "your-app-name"
                         });
                         */
                    }
                    $('pre code').each(function(i, e) {
                        hljs.highlightBlock(e)
                    });
                },
                onFailure: function(data) {
                    log("Unable to Load SwaggerUI");
                },
                docExpansion: "none",
                sorter : "alpha"
            });

            function addApiKeyAuthorization() {
                var key = $('#input_apiKey')[0].value;
                log("key: " + key);
                if(key && key.trim() != "") {
                    log("added key " + key);
                    window.authorizations.add("api_key", new ApiKeyAuthorization("api_key", key, "query"));
                }
            }

            $('#input_apiKey').change(function() {
                addApiKeyAuthorization();
            });

            // if you have an apiKey you would like to pre-populate on the page for demonstration purposes...
            /*
             var apiKey = "myApiKeyXXXX123456789";
             $('#input_apiKey').val(apiKey);
             addApiKeyAuthorization();
             */

            window.swaggerUi.load();
        });
    </script>
</head>

<body class="swagger-section">
<div id='header'>
    <div class="swagger-ui-wrap">
        <a id="logo" href="http://swagger.wordnik.com">swagger</a>
        <form id='api_selector'>
            <div class='input'><input placeholder="http://example.com/api" id="input_baseUrl" name="baseUrl" type="text"/></div>
            <div class='input'><input placeholder="api_key" id="input_apiKey" name="apiKey" type="text"/></div>
            <div class='input'><a id="explore" href="#">Explore</a></div>
        </form>
    </div>
</div>

<div id="message-bar" class="swagger-ui-wrap">&nbsp;</div>
<div id="swagger-ui-container" class="swagger-ui-wrap"></div>
</body>
</html>
