<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>

<html>
<head>
    <title>Welcome</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<body>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=UTF-8">

    <title>Campus Connect</title>

    <link rel="stylesheet" href="css/style.css" type="text/css" media="screen" charset="utf-8">

    <script src="js/jquery-1.3.1.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="js/slider.js" type="text/javascript" charset="utf-8"></script>
</head>



<div id="wrapper">

    <font align="center" color="green" size="10"> Campus Connect </font></br></br>

    <div id="slider">



        <img class="scrollButtons left" src="images/leftarrow.png">

        <div style="overflow: hidden;" class="scroll">

            <div class="scrollContainer">

                <div class="panel" id="panel_1">
                    <div class="inside">
                        <img src="icu.jpg" alt="picture" />
                        <h2>ICU Ward</h2>
                        <p>For More Information  <a href="http://flickr.com/photos/justbcuz/112479862/">Click Here</a></p>
                    </div>
                </div>

                <div class="panel" id="panel_2">
                    <div class="inside">
                        <img src="path.jpg" alt="picture" />
                        <h2>pathology Ward</h2>
                        <p>For More Information  <a href="http://flickr.com/photos/joshuacraig/2698975899/">Click Here</a></p>
                    </div>
                </div>

                <div class="panel" id="panel_3">
                    <div class="inside">
                        <img src="mat.jpg" alt="picture" />
                        <h2>Maternity ward</h2>
                        <p>For More Information <a href="http://flickr.com/photos/ruudvanleeuwen/468309897/">Click Here</a></p>
                    </div>
                </div>

                <div class="panel" id="panel_4">
                    <div class="inside">
                        <img src="iccu.jpg" alt="picture" />
                        <h2>ICCU Unit</h2>
                        <p>For More Information  <a href="http://flickr.com/photos/emikohime/294092478/">Click Here</a></p>
                    </div>
                </div>

                <div class="panel" id="panel_5">
                    <div class="inside">
                        <img src="mri.jpg" alt="picture" />
                        <h2>MRI & X-Ray</h2>
                        <p>For More Information  <a href="http://flickr.com/photos/fensterbme/499006584/">Click Here</a></p>
                    </div>
                </div>

            </div>

            <div id="left-shadow"></div>
            <div id="right-shadow"></div>

        </div>

        <img class="scrollButtons right" src="images/rightarrow.png">

    </div>

</div>

</body>
<div align="center">
    <body background="img3.jpe" align="centre" height="500" width="1000">

    </br>
    <marquee><h3><font color="blue" size="3">** Registration is free avail it!!</h3></font></marquee><br>
    <br>




    </form>
    <span align="center" style="font-size: x-small; ">
        <a href="Register.jsp">
            <img src="img2.jpg" alt="" width="100" height="50" border="0"></a>
    </span>
    <br><br>
    <h1 align="center">Smart way to connect campus people</h1>
    <br><br>
    <form action= "/authenticateUser/CampusConnect.htm" method="post">

        <table align="center" border="5">

            <tr> <td>Username :</td> <td><input type="text" name="user"/></td> </tr>
            <tr><td>Password :</td><td><input type="text" name="pass"/></td>
            <tr><td></td><td><input type="submit" name="login" value="login"></td></tr>
        </table>
    </form>
    <br>
    <font color="blue" align="center " size="3"> Copyright © 2015 DevsAtWork Group.</font><br><br>
    <font color="blue" align="middle " size="2">  <a href="contact.html"> <h3>Contact us </h3></a> </font><br><br>



</html>