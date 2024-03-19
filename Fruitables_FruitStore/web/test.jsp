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

       
    </head>

    <body>
        <!-- Nav bar file -->
        <jsp:include page="header.jsp"/>
<!--        <input id="active_link" type="hidden" value="">-->
            
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
                    <div class="col-lg-8 col-xl-9">
                        <div class="row g-4">

        <div class="col-lg-10">
            <nav>
                <div class="nav nav-tabs ">
                    <button class="nav-link active border-white border-bottom-0" type="button" role="tab"
                        id="nav-about-tab" data-bs-toggle="tab" data-bs-target="#nav-about"
                        aria-controls="nav-about" aria-selected="true"><h3>User</h3></button>
                    <button class="nav-link border-white border-bottom-0" type="button" role="tab"
                        id="nav-mission-tab" data-bs-toggle="tab" data-bs-target="#nav-mission"
                        aria-controls="nav-mission" aria-selected="false"><h3>Admin</h3></button>
                </div>
            </nav>
        <!-- Choice Start -->
        <div class="tab-content mb-5 ">                                    
                                    
            <!-- User -->
            <div class="tab-pane active bg-white  px-5 py-3" id="nav-about" role="tabpanel" aria-labelledby="nav-about-tab">
                <!-- hang 1 -->
                    <div class="row">
                        <div class="text-start col-md-12 col-lg-6">                          
                                <p class=" my-3 text-primary">User Name :</p>    
                        </div>
                        <div class="text-end col-md-12 col-lg-6">                           
                                <p class=" my-3">Phongdeptrai123</p>          
                        </div>
                    </div>
                <!-- hang 2 -->    
                    <div class="row">
                        <div class="text-start col-md-12 col-lg-6">                          
                                <p class=" my-3 text-primary">Password :</p>    
                        </div>
                        <div class="text-end col-md-12 col-lg-6">                           
                                <p class=" my-3">Phongdeptrai123</p>          
                        </div>
                    </div>
                <!-- hang 3 -->    
                    <div class="row">
                        <div class="text-start col-md-12 col-lg-6">                          
                                <p class=" my-3 text-primary">Email :</p>    
                        </div>
                        <div class="text-end col-md-12 col-lg-6">                           
                                <p class=" my-3">Phongdeptrai123@gmail.com</p>          
                        </div>
                    </div>
                <!-- hang 4 -->    
                    <div class="row">
                        <div class="text-start col-md-12 col-lg-6">                          
                                <p class=" my-3 text-primary">Phone Number :</p>    
                        </div>
                        <div class="text-end col-md-12 col-lg-6">                           
                                <p class=" my-3">01234567895</p>          
                        </div>
                    </div>
                <!-- hang 5 -->   
                    <div class="row">
                        <div class="text-start col-md-12 col-lg-6">                          
                                <p class=" my-3 text-primary">Adress :</p>    
                        </div>
                        <div class="text-end col-md-12 col-lg-6">                           
                                <p class=" my-3">Ho Chi Minh</p>          
                        </div>
                    </div>
                <!-- hang 6 -->    
                    <div class="row">
                        <div class="text-start col-md-12 col-lg-6">                          
                                <p class=" my-3 text-primary">Payment :</p>    
                        </div>
                        <div class="text-end col-md-12 col-lg-6">                           
                                <p class=" my-3">MB Bank</p>          
                        </div>
                    </div>
                 
                        
                    
                    
                
            </div>
            
            <!-- Admin -->
            <div class="tab-pane bg-white  px-5 py-3" id="nav-mission" role="tabpanel" aria-labelledby="nav-mission-tab">
                    
                        <div class="table-responsive">
                            <!-- Add Product Btn-->
                            <div class="text-end py-3">
                                <button class="btn-long border-secondary rounded-pill px-5 py-3 text-primary text-uppercase mb-4 ms-4" type="button">Add Product</button>
                            </div>
                            <!-- Tieu De Bang -->
                            <table class="table">
                                <thead>
                                  <tr>
                                    <th scope="col">Products</th>
                                    <th scope="col">Name</th>
                                    <th scope="col">Stock</th>                    
                                    <th scope="col">Update</th>
                                  </tr>
                                </thead>
                            <!-- Than bang -->    
                                <tbody>
                                    <!-- hang 1 -->
                                    <tr>
                                        <th scope="row">
                                            <div class="d-flex align-items-center">
                                                <img src="img/vegetable-item-3.png" class="img-fluid me-5 rounded-circle" style="width: 80px; height: 80px;" alt="">
                                            </div>
                                        </th>
                                        <td>
                                            <p class="mb-0 mt-4">Big Banana</p>
                                        </td>
                                        <td>
                                            <p class="mb-0 mt-4">1400</p>
                                        </td>
                                        
                                        <td>
                                            <button class="btn btn-md rounded-circle bg-light border mt-4" >
                                                <i class="fa-solid fa-sliders"></i>
                                            </button>
                                        </td>
                                    
                                    </tr>
                                    <!-- hang 2 -->
                                    <tr>
                                        <th scope="row">
                                            <div class="d-flex align-items-center">
                                                <img src="img/vegetable-item-5.jpg" class="img-fluid me-5 rounded-circle" style="width: 80px; height: 80px;" alt="" alt="">
                                            </div>
                                        </th>
                                        <td>
                                            <p class="mb-0 mt-4">Potatoes</p>
                                        </td>
                                        <td>
                                            <p class="mb-0 mt-4">1800</p>
                                        </td>
                                        
                                        <td>
                                            <button class="btn btn-md rounded-circle bg-light border mt-4" >
                                                <i class="fa-solid fa-sliders"></i>
                                            </button>
                                        </td>
                                    </tr>
                                    <!-- hang 3 -->
                                    <tr>
                                        <th scope="row">
                                            <div class="d-flex align-items-center">
                                                <img src="img/vegetable-item-2.jpg" class="img-fluid me-5 rounded-circle" style="width: 80px; height: 80px;" alt="" alt="">
                                            </div>
                                        </th>
                                        <td>
                                            <p class="mb-0 mt-4">Awesome Brocoli</p>
                                        </td>
                                        <td>
                                            <p class="mb-0 mt-4">1000</p>
                                        </td>
                                        
                                        <td>
                                            <button class="btn btn-md rounded-circle bg-light border mt-4" >
                                                <i class="fa-solid fa-sliders"></i>
                                            </button>
                                        </td>
                                    </tr>
                                    
                                    
                                </tbody>
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
        <script src="lib/easing/easing.min.js"></script>
        <script src="lib/waypoints/waypoints.min.js"></script>
        <script src="lib/lightbox/js/lightbox.min.js"></script>
        <script src="lib/owlcarousel/owl.carousel.min.js"></script>
        <script src="js/AddToCartFunction.js" defer></script>
        <script src="js/ActiveLink.js" defer></script>
        <script src="js/ViewProduct.js" defer></script>
    </body>

</html>
