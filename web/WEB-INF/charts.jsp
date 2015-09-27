<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link href='http://fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
    <script type="text/javascript">

        google.load('visualization', '1.0', {
            'packages': ['bar']
        });

        google.setOnLoadCallback(drawChart);


        function drawChart() {
            var data = google.visualization.arrayToDataTable([
                ['Followers', 'User', 'tweetName'],
                <c:forEach items="${usersDataMap}" var="entry">
                <c:forEach items="${usersDataMap.get(entry.key)}" var="innerEntry">
                ['${entry.key}', '${innerEntry.key}', '${innerEntry.value}'],
                </c:forEach>
                </c:forEach>
            ]);

            var options = {
                chart: {
                    User: 'Sheeban Shaikh',
                    subtitle: 'Twitter Detailsss',
                    'width': 900,
                    'height': 400
                }
            };


            var chart = new google.charts.Bar(document.getElementById('chart_div'));
            chart.draw(data, options);
        }

        $(document).ready(function () {

            <c:if test="${isTweetPostedSuccessful}" var="isTweetPostedSuccessful">
            if (${isTweetPostedSuccessful}) {
                alert("Tweet Successful");
            }
            </c:if>

            <c:if test="${isStatusUpdateSuccessful}" var="isStatusUpdateSuccessful">
            if (${isStatusUpdateSuccessful}) {
                alert("Bio Updated Successful");
            }
            </c:if>


            $('input.tweeting').change(function () {
                if ($(this).is(':checked')) $('div.tweet').show();
                else $('div.tweet').hide();
            }).change();

            $('input.Status').change(function () {
                if ($(this).is(':checked')) $('div.State').show();
                else $('div.State').hide();
            }).change();

            $('#btnForTweet').click(function () {
                if ($(textBoxForTweet).val() == "") {
                    alert("Please Enter Data");
                } else {
                    window.location.href = "/postTweet/CampusConnect.htm?userName=shaikhsheeban&tweetMessage=" + $("#textBoxForTweet").val();
                }
            });

            $('#btnForUpdate').click(function () {
                if ($(textBoxForUpdate).val() == "") {
                    alert("Please Enter Data");
                } else {
                    window.location.href = "/updateStatus/CampusConnect.htm?userName=shaikhsheeban&updateStatusString=" + $("#textBoxForUpdate").val();
                }
            });

        });
    </script>

<style>
    body {
        background-image: url('../images/twitter.png');
        background-size: cover;
        font-family: Montserrat;
    }

    .logo {
        width: 263px;
        height: 63px;
        background: url('http://www.designmantic.com/logo-design/create?design=8275') no-repeat;
        margin: 30px auto;
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

    #checkbox {
        border-color: rgba(0, 0, 0, 0.5)
    }

    #btnForTweet {
        width: 11%;
        height: 20px;
        box-sizing: border-;
        background: #0099FF;
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

    #btnForUpdate {
        width: 11%;
        height: 20px;
        box-sizing: border-;
        background: #0099FF;
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

    #btnForTweet:hover {
        background: #0066CC;
    }

    #btnForUpdate:hover {
        background: #0066CC;
    }

    #twitter {
        float: centre;
        position: relative;
        right: 180px;
    }

    #State {
        float: centre;
        position: relative;
        top: 10px;
    }


</style>
<body>
<div style="width: 600px;">
    <div id="chart_div" style="width: 900px; height: 500px;"></div>
</div>
<div id="tweet">
    <input type="checkbox" id="checkboxForUpdateStatus" class="tweeting"> Post a tweet <br>

    <div class="tweet">
        <input type="text" id="textBoxForTweet"> <br>
        <input type="button" id="btnForTweet" value="submit">
    </div>
</div>
<div id="State">
    <input type="checkbox" id="checkboxForTweet" class="Status"> Update Your Status <br>

    <div class="State">
        <input type="text" id="textBoxForUpdate"> <br>
        <input type="button" id="btnForUpdate" value="submit">
    </div>
</div>


</body>
</html>