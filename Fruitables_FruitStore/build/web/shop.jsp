<%@page import="DTO.Product.ProductDetail"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Random"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

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
        
        <c:set var="pageLength" value="9"/>
    </head>
    <body>    
        
        <jsp:include page="header.jsp"></jsp:include>
        
        <!-- Single Page Header start -->
        <div class="container-fluid page-header py-5">
            <img src="" class="img-fluid w-100 rounded-top" alt="">
            <h1 class="text-center text-white display-6">Shop</h1>
            <ol class="breadcrumb justify-content-center mb-0">
                <li class="breadcrumb-item"><a href="#">Home</a></li>
                <li class="breadcrumb-item"><a href="#">Pages</a></li>
                <li class="breadcrumb-item active text-white">Shop</li>
            </ol>
        </div>
        <!-- Single Page Header End -->
        
        <!-- Fruits Shop Start-->
        <div class="container-fluid fruite py-5">
            <div class="container py-5">
                <!-- Tieu De-->
                <h1 class="mb-4">Fresh fruits shop</h1>
                <div class="row g-4">
                    <div class="col-lg-12">
                        <div class="row g-4">
                            
                            <!-- Thanh search-->
                            <div class="col-xl-3">
                                <form action="DispatchServlet" method="get">
                                    <div class="input-group w-100 mx-auto d-flex">
                                        <input id="mainTypeField"type="hidden" name="type" value="${param.type}">
                                        <input id="mainValueField" type="search" name="txtSearchValue" value="${param.txtSearchValue}" class="form-control p-3" placeholder="keywords" aria-describedby="search-icon-1">
                                        <button type="submit" name="btAction" value="search" id="search-icon-1" class="input-group-text p-3">
                                            <i class="fa fa-search"></i>
                                        </button>
                                    </div>
                                </form>
                            </div>
                            
                            <!-- Dong cach ra-->
                            <div class="col-6"></div>
                            
                            <!-- Thanh phan loai-->
                            <div class="col-xl-3">
                                <div class="bg-light ps-3 py-3 rounded d-flex justify-content-between mb-4">
                                    <label for="fruits">Sort by:</label>
                                    <select id="sort_options" name="fruitlist" class="border-0 form-select-sm bg-light me-3" form="fruitform">
                                        <option value="all">Default</option>
                                        <option value="popular">Popularity</option>
                                        <option value="organic">Organic</option>
                                        <option value="cheap">From cheapest</option>
                                        <option value="expensive">From most expensive</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        
                        
                        <div class="row g-4">
                            <div class="col-lg-3">
                                <div class="row g-4">
                                    <div class="col-lg-12">
                                        
                                        <!-- Categories-->
                                        <div class="mb-3">
                                            <h4>Categories</h4>
                                            <ul class="list-unstyled fruite-categorie">
                                                <li>
                                                    <div class="d-flex justify-content-between fruite-name">
                                                        <a href="DispatchServlet?btAction=search&type=all"><i class="fas fa-apple-alt me-2"></i>All products</a>
                                                    </div>
                                                </li>
                                            <c:forEach var="category" items="${productCategoryList}">
                                                <li>
                                                    <div class="d-flex justify-content-between fruite-name">
                                                        <a href="DispatchServlet?btAction=search&type=category&txtSearchValue=${category.name}"><i class="fas fa-apple-alt me-2"></i>${category.name}</a>
                                                    </div>
                                                </li>
                                            </c:forEach>
                                            </ul>
                                        </div>
                                    </div>
                                    
                                    <!-- Featured products-->
                                    <div class="col-lg-12">
                                        <h4 class="mb-3">Featured products</h4>
                                        <%  Random rand = new Random(12345);  %>
                                        <c:forEach var="product" items="${bestSellerList}" end="5" varStatus="i">
                                            <div class="d-flex align-items-center justify-content-start" onclick="">
                                                <div class="rounded me-4" style="width: 80px;">
                                                    <img src="${product.imageLink}" class="img-fluid rounded" alt="${product.name}">
                                                </div>
                                                <div>
                                                    <h6 class="mb-2">${fn:substring(product.name, 0, 15)}</h6>
                                                    <%  request.setAttribute("randStar", rand.nextInt((5 - 3) + 1) + 3);  %>
                                                    <div class="d-flex mb-2">
                                                        <c:forEach begin="1" end="5" varStatus="i">
                                                            <c:choose>
                                                                <c:when test="${i.count le randStar}">
                                                                    <i class="fa fa-star text-secondary"></i>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <i class="fa fa-star"></i>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:forEach>
                                                    </div>
                                                </div>
                                                <div class="d-flex mb-2">
                                                    <%  request.setAttribute("priceMultiply", ((double)rand.nextInt(150-120+1)+120)/100);  %>
                                                    <h5 class="fw-bold me-2">$<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${product.price}"/></h5>
                                                    <h5 class="text-danger text-decoration-line-through">$<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${product.price * priceMultiply}"/></h5>
                                                </div>
                                            </div>
                                            <br/>
                                        </c:forEach>
                                        
                                        <!-- View more button -->
                                        <div class="d-flex justify-content-center my-4">
                                            <a href="#" class="btn border border-secondary px-4 py-3 rounded-pill text-primary w-100">View More</a>
                                        </div>
                                    </div>
                                    
                                    <!-- Banner -->
                                    <div class="col-lg-12">
                                        <div class="position-relative">
                                            <img src="https://static.vecteezy.com/system/resources/previews/026/857/795/non_2x/of-colorful-assortment-of-fruits-and-vegetables-on-a-clean-white-background-with-copy-space-photo.jpg" class="img-fluid w-100 rounded" alt="">
                                            <div class="position-absolute" style="top: 50%; right: 10px; transform: translateY(-50%);">
                                                <h3 class="text-secondary fw-bold">Fresh <br> Fruits <br> Banner</h3>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <!-- Cac San Pham-->

                            <div class="col-lg-9">
                                <div class="row g-4 justify-content-center">
                                    <c:choose>
                                        <c:when test="${empty searchedList || (fn:length(searchedList) le 0)}">
                                            <p style="font-size: 28px; text-align: center; margin: 50px; padding: 50px;">There is no entry on <span style="font-style: italic;">"${txtSearchValue}"</span>, please enter a different keyword</p>
                                        </c:when>
                                        <c:otherwise>
                                            <c:forEach var="product" items="${searchedList}">
                                                <div class="col-md-6 col-lg-6 col-xl-4">
                                                    <form class="addToCartFunction viewProduct rounded check_out_of_stock">
                                                        <input type="hidden" name="productID" value="${product.id}">
                                                        <div class="rounded position-relative fruite-item">
                                                            <div class="fruite-img">
                                                                <img src="${product.imageLink}" class="img-fluid w-100 rounded-top" alt="${product.name}">
                                                            </div>
                                                            <div class="text-white bg-secondary px-3 py-1 rounded position-absolute" style="top: 10px; left: 10px;">${product.category}</div>
                                                            <div class="text-white bg-secondary px-3 py-1 rounded position-absolute" style="top: 10px; right: 10px; text-align: center;">
                                                                Stock:<br/>
                                                                <span class="stockField">${product.inStock}</span>
                                                            </div>
                                                            <div class="p-4 border border-secondary border-top-0 rounded-bottom">
                                                                <h4>${product.name}</h4>
                                                                <p>${product.description}</p>
                                                                <div class="d-flex justify-content-between flex-lg-wrap">
                                                                    <p class="text-dark fs-5 fw-bold mb-0">$${product.price}/${product.productType}</p>
                                                                    <button type="submit" name="btAction" value="AddToCart" class="addToCartBt btn border border-secondary rounded-pill px-3 text-primary">
                                                                        <i class="fa fa-shopping-bag me-2 text-primary"></i> Add to cart
                                                                    </button>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </form>
                                                </div>
                                            </c:forEach>
                                        </c:otherwise>
                                    </c:choose>
                                            
                                    <!--Thanh cuộn trang-->
                                    <div class="col-12">
                                        <fmt:formatNumber var="pageLimit" value="${(listLength / pageLength)  + (1 - ((listLength / pageLength) % 1)) % 1}" type="number" maxFractionDigits="0" pattern="#"/>
                                        <div class="pagination d-flex justify-content-center mt-5">
                                            <input id="switch_page_function" type="hidden" name="btAction" value="search">
                                            <button name="page" value="${param.page - 1}" id="page_backward" href="#" class="rounded switch_page" <%= (Integer.parseInt(request.getParameter("page")) <= 1)? "disabled": "" %>>&laquo;</button>
                                            <c:forEach varStatus="i" begin="1" end="${pageLimit}">
                                                <button name="page" value="${i.count}" href="#" class="rounded switch_page">${i.count}</button>
                                            </c:forEach>
                                                <button name="page" value="${param.page + 1}" id="page_forward" class="rounded switch_page" <%= (Integer.parseInt(request.getParameter("page")) >= Integer.parseInt(String.valueOf(pageContext.getAttribute("pageLimit"))))? "disabled": "" %>>&raquo;</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Fruits Shop End-->

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
                    <div id="contact_info" class="col-lg-3 col-md-6">
                        <div class="footer-item">
                            <h4 class="text-light mb-3">Contact</h4>
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
        
        <script src="js/MessageAppear.js" defer></script>
        <script src="js/AddToCartFunction.js" defer></script>
        <script src="js/ActiveLink.js" defer></script>
        <script src="js/ViewProduct.js" defer></script>
        <script src="js/CheckOutOfStock.js" defer></script>
        <script src="js/SwitchPageFunction.js" defer></script>
    </body>
</html>
