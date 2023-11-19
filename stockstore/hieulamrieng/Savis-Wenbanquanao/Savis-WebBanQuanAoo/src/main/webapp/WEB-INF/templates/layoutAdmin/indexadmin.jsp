<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8" />
    <link rel="apple-touch-icon" sizes="76x76" href="/assets2/imgadmin/apple-icon.png">
    <link rel="icon" type="image/png" href="/assets2/imgadmin/favicon.png">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <title>
 Admin
    </title>
    <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0, shrink-to-fit=no' name='viewport' />
    <!--     Fonts and icons     -->
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700,200" rel="stylesheet" />
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" rel="stylesheet">
    <!-- CSS Files -->
    <link href="/static/assets2/cssadmin/bootstrap.min.css" rel="stylesheet" />
    <link href="/assets2/cssadmin/paper-dashboard.css?v=2.0.1" rel="stylesheet" />
    <!-- CSS Just for demo purpose, don't include it in your project -->
    <link href="/assets2/demoadmin/demo.css" rel="stylesheet" />
</head>

<body class="">
<div class="wrapper ">
    <div class="sidebar" data-color="white" data-active-color="danger">
        <div class="logo">
            <a href="https://www.creative-tim.com" class="simple-text logo-mini">
                <div class="logo-image-small">
                    <img src="/assets2/imgadmin/logo-small.png">
                </div>
                <!-- <p>CT</p> -->
            </a>
            <a href="/account/about/${user.id}" class="simple-text logo-normal">
                ADMIN
                <!-- <div class="logo-image-big">
                  <img src="../assets/img/logo-big.png">
                </div> -->
            </a>
        </div>
        <div class="sidebar-wrapper">
            <ul class="nav">
                <li class=" ">
                    <a href="/Admin/Views">
                        <i class="nc-icon nc-bank"></i>
                        <p>Dashboard</p>
                    </a>
                </li>
                <li>
                    <a href="/Admin/Category">
                        <i class="nc-icon nc-tile-56"></i>
                        <p>Loai</p>
                    </a>
                </li>
                <li>
                    <a href="/Admin/Table">
                        <i class="nc-icon nc-tile-56"></i>
                        <p>San Pham</p>
                    </a>
                </li>
                <li>
                    <a href="/Admin/Account">
                        <i class="nc-icon nc-tile-56"></i>
                        <p>Tai khoan</p>
                    </a>
                </li>
                <li>
<%--                    <a href="./map.html">--%>
<%--                        <i class="nc-icon nc-pin-3"></i>--%>
<%--                        <p>Maps</p>--%>
<%--                    </a>--%>
                </li>
                <li>
<%--                    <a href="./notifications.html">--%>
<%--                        <i class="nc-icon nc-bell-55"></i>--%>
<%--                        <p>Notifications</p>--%>
<%--                    </a>--%>
                </li>

                <li>
<%--                    <a href="./typography.html">--%>
<%--                        <i class="nc-icon nc-caps-small"></i>--%>
<%--                        <p>Typography</p>--%>
<%--                    </a>--%>
                </li>
                <li class="active-pro">
<%--                    <a href="./upgrade.html">--%>
<%--                        <i class="nc-icon nc-spaceship"></i>--%>
<%--                        <p>Upgrade to PRO</p>--%>
<%--                    </a>--%>
                </li>
            </ul>
        </div>
    </div>
    <div class="main-panel">
        <!-- Navbar -->
        <tiles:insertAttribute name="menuadmin" />
        <tiles:insertAttribute name="bodyadmin" />
        <tiles:insertAttribute name="footeradmin" />
        <!-- End Navbar -->


    </div>
</div>
<!--   Core JS Files   -->
<script src="/assets2/jadmin/core/jquery.min.js"></script>
<script src="/assets2/jadmin/core/popper.min.js"></script>
<script src="/assets2/jadmin/core/bootstrap.min.js"></script>
<script src="/assets2/jadmin/plugins/perfect-scrollbar.jquery.min.js"></script>
<!--  Google Maps Plugin    -->
<script src="https://maps.googleapis.com/maps/api/js?key=YOUR_KEY_HERE"></script>
<!-- Chart JS -->
<script src="/assets2/jadmin/plugins/chartjs.min.js"></script>
<!--  Notifications Plugin    -->
<script src="/assets2/jadmin/plugins/bootstrap-notify.js"></script>
<!-- Control Center for Now Ui Dashboard: parallax effects, scripts for the example pages etc -->
<script src="/assets2/jadmin/paper-dashboard.min.js?v=2.0.1" type="text/javascript"></script><!-- Paper Dashboard DEMO methods, don't include it in your project! -->
<script src="/assets2/demo/demo.js"></script>
<script>
    $(document).ready(function() {
        // Javascript method's body can be found in assets/assets-for-demo/js/demo.js
        demo.initChartsPages();
    });
</script>
</body>

</html>
