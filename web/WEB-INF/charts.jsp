<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript">
        google.load('visualization', '1.0', {
            'packages' : [ 'bar' ]
        });

        google.setOnLoadCallback(drawChart);

        function drawChart() {
            var data = google.visualization.arrayToDataTable([
                ['User', 'Followers'],
                <c:forEach items="${usersDataMap}" var="entry">
                [ '${entry.key}', '${entry.value}' ],
                </c:forEach>
            ]);

            var options = {
                chart: {
                    User: 'Sheeban Shaikh',
                    subtitle: 'Twitter Details',
                    'width' : 900,
                    'height' : 500
                }
            };


            var chart = new google.charts.Bar(document.getElementById('chart_div'));
            chart.draw(data, options);
        }
    </script>
</head>
<body>
<div style="width: 600px;">
    <div id="chart_div" style="width: 900px; height: 500px;"></div>
</div>
</body>
</html>