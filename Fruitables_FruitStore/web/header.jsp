<%@page import="DTO.Cart.CartItemList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
    <meta charset="utf-8">
    <title>Fruitables - Vegetable Website Template</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="" name="keywords">
    <meta content="" name="description">

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&family=Raleway:wght@600;800&display=swap" rel="stylesheet"> 

    <!-- Icon Font Stylesheet -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

    <!-- Libraries Stylesheet -->
    <link href="css/lightbox.min.css" rel="stylesheet">
    <link href="css/owl.carousel.min.css" rel="stylesheet">


    <!-- Customized Bootstrap Stylesheet -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Template Stylesheet -->
    <link href="css/style.css" rel="stylesheet">
    <link href="css/newStyle.css" rel="stylesheet">
    
</head>


 <!--?Spinner Start--> 
<div id="spinner" class="spinner show w-100 vh-100 bg-white position-fixed translate-middle top-50 start-50  d-flex align-items-center justify-content-center">
    <div class="spinner-grow text-primary" role="status" style="width: 5rem; height: 5rem"></div>
</div>
 <!--Spinner End--> 


<!-- Navbar start -->
<div class="container-fluid fixed-top green">
    <div class="container topbar bg-primary d-none d-lg-block">
        <div class="d-flex justify-content-between">
            <div class="top-info ps-2">
                <small class="me-3"><i class="fas fa-map-marker-alt me-2 text-secondary"></i> <a href="#" class="text-white">123 Street, New York</a></small>
                <small class="me-3"><i class="fas fa-envelope me-2 text-secondary"></i><a href="#" class="text-white">Email@Example.com</a></small>
            </div>
            <div class="top-link pe-2">
                <a href="Fruitable - Privacy Policy.html" class="text-white"><small class="text-white mx-2">Privacy Policy</small>/</a>
                <a href="Fruitable - Privacy Policy.html" class="text-white"><small class="text-white mx-2">Terms of Use</small>/</a>
                <a href="Fruitable - Privacy Policy.html" class="text-white"><small class="text-white ms-2">Sales and Refunds</small></a>
            </div>
        </div>
    </div>
</div>
<div class="container-fluid fixed-top white">
    <div class="container px-0">
        <nav class="navbar navbar-light bg-white navbar-expand-xl">
            <a href="index.html" class="navbar-brand"><h1 class="text-primary display-6">Fruitables</h1></a>
            <button class="navbar-toggler py-2 px-3" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                <span class="fa fa-bars text-primary"></span>
            </button>
            <div class="collapse navbar-collapse bg-white" id="navbarCollapse">
                <div class="navbar-nav mx-auto">
                    <a id="nav-home" href="/Home" class="nav-item nav-link active">Home</a>
                    <a id="nav-shop" href="/Search?type=all" class="nav-item nav-link">Shop</a>

                    <div class="nav-item dropdown">
                        <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">Pages</a>
                        <div class="dropdown-menu m-0 bg-secondary rounded-0">
                            <a href="DispatchServlet?btAction=cart" class="dropdown-item">Cart</a>
                            <a href="DispatchServlet?btAction=cart" class="dropdown-item">Checkout</a>
                            <a href="404.jsp" class="dropdown-item">404 Page</a>
                        </div>
                    </div>
                    <a href="#contact_info" class="nav-item nav-link">Contact</a>
                </div>
                <div class="d-flex m-3 me-0" id="user_space">
                    <button class="btn-search btn border border-secondary btn-md-square rounded-circle bg-white me-4" data-bs-toggle="modal" data-bs-target="#searchModal"><i class="fas fa-search text-primary"></i></button>
                    <a href="DispatchServlet?btAction=cart" class="position-relative me-4 my-auto">
                        <i class="fa fa-shopping-bag fa-2x"></i>
                        <span id="cart_item_num" class="position-absolute bg-secondary rounded-circle d-flex align-items-center justify-content-center text-dark px-1" style="top: -5px; left: 15px; height: 20px; min-width: 20px;">
                            <c:choose>
                                <c:when test="${empty sessionScope.cart}">
                                    0
                                </c:when>
                                <c:otherwise>
                                    <%= ((CartItemList)request.getSession().getAttribute("cart")).getTotalQuantity() %>
                                </c:otherwise>
                            </c:choose>
                        </span>
                    </a>
                    <c:if test="${not empty sessionScope.user}">
                        <div class="navbar-nav mx-auto left-auto">
                            <a href="" class="nav-item" id="user_username">
                                <i class="fas fa-user"></i> ${sessionScope.user.username}
                            </a>
                            <a href="FunctionDispatchServlet?btAction=logout" class="nav-item" >
                                <i class="fas fa-sign-in-alt"> Logout</i>
                            </a>
                        </div>
                    </c:if>
                    <c:if test="${empty sessionScope.user}">
                        <form action="DispatchServlet" method="get">
                            <div class="navbar-nav mx-auto left-auto">
                                <a href="DispatchServlet?btAction=registration&state=login" class="nav-item"style="margin-right: 10px" >Login</a><!-- comment -->
                                <a href="DispatchServlet?btAction=registration&state=register" class="nav-item">Register</a><!-- comment -->
                            </div>  
                        </form>
                    </c:if>
                </div>
            </div>
        </nav>
    </div>
</div>

<!-- Modal Search Start -->
<div class="modal fade" id="searchModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-fullscreen">
        <div class="modal-content rounded-0">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Search by keyword</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body d-flex align-items-center">
                <form style="flex: 1;" action="DispatchServlet" method="get">
                    <div class="input-group w-75 mx-auto d-flex">
                        <input type="hidden" name="type" value="name">
                        <input type="search" class="form-control p-3" placeholder="keywords" aria-describedby="search-icon-1" name="txtSearchValue" value="">
                        <button type="submit" name="btAction" value="search" id="search-icon-1" class="input-group-text p-3"><i class="fa fa-search"></i></button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<!-- Modal Search End -->

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
<script>
    window.addEventListener('load', function(event) {
        var spinner = document.querySelector('div.spinner');
        spinner.classList.remove('show');
        spinner.addEventListener('transitionend', () => spinner.remove() );
    });
</script>
