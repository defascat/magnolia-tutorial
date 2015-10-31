<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="sv" lang="sv">
<head>
<title>${content.title!}</title>
    [@cms.page /]
</head>
<body>
    <h1>${content.headline!}</h1>
    <div id="container">
        <div id="content">
            [@cms.area name="content"/]
        </div>
    </div>
</body>
</html>