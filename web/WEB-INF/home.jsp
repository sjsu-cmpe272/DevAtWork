<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<html>
<head>


    <meta http-equiv="Content-type" content="text/html; charset=UTF-8">

    <title>Campus Connect</title>

    <link href='http://fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
    <script type="text/javascript" charset="utf-8">
        var jq = $(document).ready(function () {
            console.log("Loadeddddddd");

            alert("Twitter UserName does not exists");


            $('#btn').click(function () {
                if ($('#username').val() == " " || $('#username').val() == "") {
                    alert("Please enter Twitter Name");
                } else {
                    window.location.href = '/authenticateUser/CampusConnect.htm';
                }
            });

        });


    </script>


    <style>
        body {
            background: url('http://pre10.deviantart.net/6f68/th/pre/f/2012/274/3/a/twitter_minimalistic_background_by_vaughnwhiskey-d5gityo.png') no-repeat fixed center center;
            background-size: cover;
            font-family: Montserrat;
        }

        .logo {
            width: 263px;
            height: 63px;
            background: url('http://www.designmantic.com/logo-design/create?design=8275') no-repeat;
            margin: 30px auto;
        }

        .login-block {
            width: 300px;
            padding: 20px;
            background: #fff;
            border-radius: 5px;
            border-top: 5px solid #0099FF;
            margin: 0 auto;
        }

        .login-block h1 {
            text-align: center;
            color: #0099FF;
            font-size: 18px;
            text-transform: uppercase;
            margin-top: 0;
            margin-bottom: 20px;
        }

        .login-block input {
            width: 100%;
            height: 42px;
            box-sizing: border-box;
            border-radius: 5px;
            border: 1px solid #ccc;
            margin-bottom: 20px;
            font-size: 14px;
            font-family: Montserrat;
            padding: 0 20px 0 50px;
            outline: none;
        }

        .login-block input#username {
            background: #fff url('http://i.imgur.com/u0XmBmv.png') 20px top no-repeat;
            background-size: 16px 80px;
        }

        .login-block input#username:focus {
            background: #fff url('http://i.imgur.com/u0XmBmv.png') 20px bottom no-repeat;
            background-size: 16px 80px;
        }

        .login-block input:active, .login-block input:focus {
            border: 1px solid #0066CC;
        }

        #btn {
            width: 100%;
            height: 40px;
            background: #0099FF;
            box-sizing: border-box;
            border-radius: 5px;
            border: 1px solid #FFFFFF;
            color: #fff;
            font-weight: bold;
            text-transform: uppercase;
            font-size: 14px;
            font-family: Montserrat;
            outline: none;
            cursor: pointer;
        }

        #btn:hover {
            background: #0066CC;
        }


    </style>

</head>

<form>

    <div class="logo"></div>
    <div class="login-block">
        <h1>Login</h1>

        <input type="text" value="" placeholder="Twitter Id" id="username"/>
        <input type="button" id="btn" value="submit"/>


    </div>
</form>
</body>

</html>
