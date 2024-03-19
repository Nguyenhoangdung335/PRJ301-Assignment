<%@page import="DTO.User.SiteUser"%>
<%@page import="java.net.URL"%>
<%@page import="DTO.Product.*"%>
<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>Fruitables - Vegetable Website </title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="" name="keywords">
        <meta content="" name="description">
        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&family=Raleway:wght@600;800&display=swap" rel="stylesheet"> 

        <!-- Icon Font Stylesheet -->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link href="css/lightbox.min.css" rel="stylesheet">
        <link href="css/owl.carousel.min.css" rel="stylesheet">


        <!-- Customized Bootstrap Stylesheet -->
        <link href="css/bootstrap.min.css" rel="stylesheet">

        <!-- Template Stylesheet -->
        <link href="css/style.css" rel="stylesheet">
        <link href="css/newStyle.css" rel="stylesheet">
        <link href="css/speech-bubble.css" rel="stylesheet">
        <link href="css/UserStyle.css" rel="stylesheet">

        <%  //request.setAttribute("pageLength", (float)((List<ProductDetail>)request.getAttribute("searchedList")).size() / 9);  %>
        <fmt:formatNumber var="pageLimit" value="${pageLength + (1 - (pageLength % 1)) % 1}" type="number" maxFractionDigits="0" pattern="#"/>
        <c:set var="user" value="${sessionScope.user}"/>
</head>

<body>
    <!-- Nav bar file -->
    <jsp:include page="header.jsp"/>
    <!--        <input id="active_link" type="hidden" value="">-->
    
    <input type="hidden" name="isAdmin" value="${user.isAdmin}">
    
    <!-- Single Page Header start -->
    <div class="container-fluid page-header py-5">
        <img src="" class="img-fluid w-100 rounded-top" alt="">
        <h1 class="text-center text-white display-6">User</h1>
        <ol class="breadcrumb justify-content-center mb-0">
            <li class="breadcrumb-item"><a href="#">Home</a></li>
            <li class="breadcrumb-item"><a href="#">Pages</a></li>
            <li class="breadcrumb-item active text-white">Shop</li>
        </ol>
    </div>
    <!-- Single Page Header End -->

    <!-- 2Tabs admin Start -->
    <div class="container-fluid py-5 mt-5 2tab hero-header">
        <div class="container py-1">
            <div class="row g-4 mb-5">
                <div class="col-lg-9">
                    <div class="row g-4">

                        <div class="col-lg-12">
                            <nav>
                                <div class="nav nav-tabs ">
                                    <button class="nav-link active border-white border-bottom-0" type="button" role="tab"
                                            id="nav-about-tab" data-bs-toggle="tab" data-bs-target="#nav-about"
                                            aria-controls="nav-about" aria-selected="true"><h3>User Info</h3></button>
                                    <button class="nav-link border-white border-bottom-0" type="button" role="tab"
                                            id="nav-mission-tab" data-bs-toggle="tab" data-bs-target="#nav-mission"
                                            aria-controls="nav-mission" aria-selected="false"><h3>Product Management</h3></button>
                                </div>
                            </nav>
                            <!-- Choice Start -->
                            <div class="tab-content mb-5 ">                                    

                                <!-- User -->
                                <div class="tab-pane active bg-white px-5 py-3" id="nav-about" role="tabpanel" aria-labelledby="nav-about-tab">
                                    <div class="text-end text-primary fw-bold">
                                        <label for="user-checkbox" class="form-check-label">Enabled edit </label>
                                        <input type="checkbox" id="user-checkbox" class="form-check-input">
                                    </div>
                                    
                                    <!-- hang 1 -->
                                    <div class="row justify-content-between">
                                        <div class="text-start col-md-4 col-lg-3">                          
                                            <label for="username" class=" my-3 text-primary fw-bold">User Name</label>    
                                        </div>
                                        <div class="text-end col-md-6 col-lg-7">                           
                                            <input class="text-end form-control border-2 my-3" type="text" name="txtUsername" id="username" value="${user.username}" data-is-sent="1" disabled>     
                                        </div>
                                    </div>
                                    <!-- hang 2 -->    
                                    <div class="row justify-content-between">
                                        <div class="text-start col-md-4 col-lg-3">                          
                                            <label for="password" class=" my-3 text-primary fw-bold">Password</label>    
                                        </div>
                                        <div class="text-end col-md-6 col-lg-7">                           
                                            <input class="text-end form-control border-2 my-3" type="password" name="txtPassword" id="password" value="${user.password}" data-is-sent="1" disabled>        
                                        </div>
                                    </div>
                                    <!-- hang 3 -->    
                                    <div class="row justify-content-between">
                                        <div class="text-start col-md-4 col-lg-3">                          
                                            <label for="email" class=" my-3 text-primary fw-bold">Email</label>    
                                        </div>
                                        <div class="text-end col-md-6 col-lg-7">                           
                                            <input class="text-end form-control border-2 my-3" input="email" name="txtEmail" id="email" value="${user.email}" data-is-sent="1" disabled>         
                                        </div>
                                    </div>
                                    <!-- hang 4 -->    
                                    <div class="row justify-content-between">
                                        <div class="text-start col-md-4 col-lg-3">                          
                                            <label for="phone_number" class=" my-3 text-primary fw-bold">Phone Number</label>    
                                        </div>
                                        <div class="text-end col-md-6 col-lg-7">                           
                                            <input class="text-end form-control border-2 my-3" type="tel" pattern="[0-9]{10}" maxlength="10" name="txtPhoneNumber" id="phone_number" value="${user.phoneNumber}" placeholder="0123456789" data-is-sent="1" disabled>
                                        </div>
                                    </div>
                                    <!-- hang 5 -->   
                                    <div class="row justify-content-between">
                                        <div class="text-start col-md-4 col-lg-3">                          
                                            <label for="address" class=" my-3 text-primary fw-bold">Address</label>    
                                        </div>
                                        <div class="text-end col-md-6 col-lg-7" id="address_select"> 
                                            <button style="font-size: 1.2em;" class="btn border border-secondary rounded-pill text-primary fw-bold my-3" type="button" name="addrAddBt" id="address_add_button" disabled>
                                                <i class="fa-solid fa-plus"></i>
                                            </button>
                                            <select class="text-end form-select border-2 my-3" id="address" data-is-sent="1" disabled>
                                                <c:forEach var="address" items="${user.shippingAddresses}" varStatus="i">
                                                    <option value="${i.index}" selected="${i.index eq user.defaultAddressIndex}">${address}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="row justify-content-end">
                                        <div class="col-md-12 col-lg-9 text-end">
                                            <button style="width: 35%" class="btn border border-secondary rounded-pill text-primary fw-bold my-3 mx-2" type="button" id="user_update_button" name="userUpdateBt" disabled>
                                                Update user
                                            </button>
                                            <button style="width: 35%" class="btn border border-secondary rounded-pill text-primary fw-bold my-3" type="button" id="logout_button" name="logOutBt" >
                                                Log out
                                            </button>
                                        </div>
                                    </div>
                                </div>

                                <!-- Admin -->
                                <div class="tab-pane bg-white px-5 py-3" id="nav-mission" role="tabpanel" aria-labelledby="nav-mission-tab">

                                    <div class="table-responsive">
                                        <!-- Tieu De Bang -->
                                            <table class="table">
                                                <thead>
                                                    <tr>
                                                        <th scope="col">No.</th>
                                                        <th scope="col" id="Table-image">Image</th>
                                                        <th scope="col" >Name</th>
                                                        <th scope="col" >In Stock</th>
                                                        <th scope="col"></th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach var="product" items="${productList}" varStatus="status">
                                                        <tr scope="row">
                                                            <td><p>${status.count}</p></td>
                                                            <td><img src="${product.imageLink}" alt="fruit picture"></td>
                                                            <td><p>${product.name}</p></td>
                                                            <td><p>${product.inStock}</p></td>
                                                            <td colspan="1">
                                                                <button class="btn btn-md rounded-circle bg-light border" onclick="window.location.href = 'DispatchServlet?btAction=view&productID=${product.id}&type=update';">
                                                                    <i class="fa-solid fa-sliders"></i>
                                                                </button>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                        <tr scope="row" id="add_product_row">
                                                            <td colspan="5">
                                                                <a href="DispatchServlet?btAction=view&productID=&type=new" class="hover_underline_animation">
                                                                    <i class="fa-solid fa-plus"></i> Add new product
                                                                </a>
                                                            </td>
                                                        </tr>
                                                </tbody>
                                            </table>
                                        <!-- Thanh keo -->        
                                        </table>
                                        <div class="text-center col-12">
                                            <div class="pagination d-flex justify-content-center mt-5">
                                                <a href="#" class="rounded">&laquo;</a>
                                                <a href="#" class="active rounded">1</a>
                                                <a href="#" class="rounded">2</a>
                                                <a href="#" class="rounded">3</a>                                   
                                                <a href="#" class="rounded">&raquo;</a>
                                            </div>
                                        </div>

                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- 2Tabs admin End -->

<!-- Footer Start -->
<div class="container-fluid bg-dark text-white-50 footer pt-5 mt-5">
    <div class="container py-5">
        <!-- Logo va thanh search -->
        <div class="pb-4 mb-4" style="border-bottom: 1px solid rgba(226, 175, 24, 0.5) ;">                   
            <div class="row g-4">
                <div class="col-lg-3">
                    <a href="home.html">
                        <h1 class="text-primary mb-0">Fruitables</h1>
                        <p class="text-secondary mb-0">Fresh products</p>
                    </a>
                </div>
                <div class="col-lg-6">
                    <div class="position-relative mx-auto">
                        <input class="form-control border-0 w-100 py-3 px-4 rounded-pill" type="number" placeholder="Your Email">
                        <button type="submit" class="btn btn-primary border-0 border-secondary py-3 px-4 position-absolute rounded-pill text-white" style="top: 0; right: 0;">Subscribe Now</button>
                    </div>
                </div>
                <div class="col-lg-3">
                    <div class="d-flex justify-content-end pt-3">
                        <a class="btn  btn-outline-secondary me-2 btn-md-square rounded-circle" href=""><i class="fab fa-twitter"></i></a>
                        <a class="btn btn-outline-secondary me-2 btn-md-square rounded-circle" href=""><i class="fab fa-facebook-f"></i></a>
                        <a class="btn btn-outline-secondary me-2 btn-md-square rounded-circle" href=""><i class="fab fa-youtube"></i></a>
                        <a class="btn btn-outline-secondary btn-md-square rounded-circle" href=""><i class="fab fa-linkedin-in"></i></a>
                    </div>
                </div>
            </div>
        </div>

        <div class="row g-5">
            <!-- Why People Like us! -->
            <div class="col-lg-3 col-md-6">
                <div class="footer-item">
                    <h4 class="text-light mb-3">Why People Like us!</h4>
                    <p class="mb-4">typesetting, remaining essentially unchanged. It was 
                        popularised in the 1960s with the like Aldus PageMaker including of Lorem Ipsum.</p>
                    <a href="" class="btn border-secondary py-2 px-4 rounded-pill text-primary">Read More</a>
                </div>
            </div>

            <!-- Shop info -->
            <div class="col-lg-3 col-md-6">
                <div class="d-flex flex-column text-start footer-item">
                    <h4 class="text-light mb-3">Shop Info</h4>
                    <a class="btn-link" href="">About Us</a>
                    <a class="btn-link" href="">Contact Us</a>
                    <a class="btn-link" href="">Privacy Policy</a>
                    <a class="btn-link" href="">Terms & Condition</a>
                    <a class="btn-link" href="">Return Policy</a>
                    <a class="btn-link" href="">FAQs & Help</a>
                </div>
            </div>

            <!-- Account -->
            <div class="col-lg-3 col-md-6">
                <div class="d-flex flex-column text-start footer-item">
                    <h4 class="text-light mb-3">Account</h4>
                    <a class="btn-link" href="">My Account</a>
                    <a class="btn-link" href="">Shop details</a>
                    <a class="btn-link" href="">Shopping Cart</a>
                    <a class="btn-link" href="">Wishlist</a>
                    <a class="btn-link" href="">Order History</a>
                    <a class="btn-link" href="">International Orders</a>
                </div>
            </div>

            <!-- Contact -->
            <div class="col-lg-3 col-md-6">
                <div class="footer-item">
                    <h4 class="text-light mb-3" id="contact_info">Contact</h4>
                    <p>Address: 1429 Netus Rd, NY 48247</p>
                    <p>Email: Example@gmail.com</p>
                    <p>Phone: +0123 4567 8910</p>
                    <p>Payment Accepted</p>
                    <img src="img/payment.png" class="img-fluid" alt="">
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Footer End -->

<!-- Back to Top -->
<a href="#" class="btn btn-primary border-3 border-primary rounded-circle back-to-top"><i class="fa fa-arrow-up"></i></a>   

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="js/MessageAppear.js"></script>
<script src="js/ViewProduct.js" defer></script>
<script src="js/UserScript.js" defer></script>
<script>
    var UserCheckbox = document.querySelector("#user-checkbox");
    var UserInput = document.querySelectorAll('[data-is-sent="1"]');
    var updateBt = document.querySelector('button#user_update_button');
    var addAddrBt = document.querySelector('button#address_add_button');
    var isAdmin = document.querySelector('input[name="isAdmin"]').value === 'true';
    UserCheckbox.addEventListener("change", () => {
        if (UserCheckbox.checked) {
            UserInput.forEach(input => input.disabled = false );
            updateBt.disabled = false;
            addAddrBt.disabled = false;
        } else {
            UserInput.forEach(input => input.disabled = true );
            updateBt.disabled = true;
            addAddrBt.disabled = true;
        }
    });
    if (!isAdmin)
        document.querySelector('button#nav-mission-tab').style.display = 'none';
</script>
</body>

</html>
