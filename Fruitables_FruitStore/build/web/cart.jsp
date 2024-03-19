<%@page import="DTO.Cart.CartItemList"%>
<%@page import="DTO.Cart.ShoppingCartItems"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        
        <style>
            .select_field {
                width: 12ch;
                text-align: right;
                padding-right: 0.5em;
            }
        </style>
        
        <% CartItemList cart = (CartItemList)request.getSession().getAttribute("cart"); %>
    </head>
    <body>    
        <!-- Navbar start -->
        <jsp:include page="header.jsp"/>
        <!-- Navbar End -->
        
        <!-- Single Page Header start -->
        <div class="container-fluid page-header py-5 ">
            <h3 class="text-center text-primary display-6">YOUR CART</h3>            
            <ol class="breadcrumb justify-content-center mb-0">                
                <li class=" text-primary display-7 p-2">User ID:</li>
                <li class=" text-primary display-7 p-2"><%= ( cart == null || cart.getUserID() < 0)? "NaN": cart.getUserID() %></li>
            </ol>
            <ol class="breadcrumb justify-content-center mb-0">
                <li class="text-primary display-7 p-2">Cart ID:</li>
                <li class="text-primary display-7 px-3 py-2"><%= (cart == null ||cart.getCartID() < 0)? "NaN": cart.getCartID() %></li>
            </ol>
        </div>
        <!-- Single Page Header End -->


        <!-- Cart Page Start -->
        <div class="container-fluid pt-5">
            <div class="container">
                
                <!-- Cart-->
                <div class="table-responsive ">
                    <form class="submitOnReload deleteCartFunction saveCartForm makeOrderForm">
                    <table class="table">
                        <!-- Header-->
                        <thead>
                          <tr>
                            <th scope="col"></th>                            
                            <th scope="col">Products</th>
                            <th scope="col">Name</th>
                            <th scope="col">Price</th>
                            <th scope="col">Quantity</th>
                            <th scope="col">Coupon</th>
                            <th scope="col">SubTotal</th>
                            <th scope="col">Remove this item</th>
                          </tr>
                        </thead>
                        
                        <!-- Body từng loại san pham -->
                        <tbody>
                        <c:if test="${not empty cart}">
                        <c:forEach var="productQuantity" items="${cart}" varStatus="i">
                            <tr class="productRow">
                                <c:set var="product" value="${productQuantity.key}"/>
                                <input type="hidden" name="productID" value="${product.id}" data-is-sent="1">
                                <td><p class="mb-0 mt-4 ">${i.count}</p></td>
                                <td>
                                    <div class="d-flex align-items-center">
                                        <img src="${product.imageLink}" class="img-fluid me-5 rounded-circle" style="width: 80px; height: 80px;" alt="${product.name}">
                                    </div>
                                </td>
                                <td><p class="mb-0 mt-4">${product.name}</p></td>
                                <td><span style="display: block;" class="mb-0 mt-4">$<p style="display: inline;" class="txtPrice"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${product.price}"/></p></span></td>
                                <td><input style="width: 8ch;" class="txtQuantity mb-0 mt-4 form-control form-control-sm text-center border-0" type="number" min="0" name="txtQuantity" value="${productQuantity.value}" data-is-sent="1"></td>
                                <td>
                                    <!-- Hidden input field for variation -->
                                    <c:forEach var="proConfig" items="${variationList}">
                                        <c:if test="${proConfig.proItemId eq product.id}">
                                            <c:forEach var="value" items="${proConfig.variations}" varStatus="i">
                                                <input class="variation" type="hidden" name="${value.value}" value="${value.pricePercent}" data-id="${proConfig.configId[i.index]}">
                                            </c:forEach>
                                        </c:if>
                                    </c:forEach>
                                    <!--  -->
                                    <p class="coupon_field mb-0 mt-4" data-id="-1" data-value="0" data-percent="1">0%</p>
                                    <input class="chosen_coupon" type="hidden" name="configID" value="-1" data-is-sent="1">
                                </td>
                                <td><span style="display: block;" class="mb-0 mt-4">$<p style="display: inline;" class="txtSubTotal">0</p></span></td>
                                <td>
                                    <button type="button" class="deleteCartBt btn btn-md rounded-circle bg-light border mt-4 px-4" style="border-left: 50px">
                                        <i class="fa fa-times text-danger"></i>
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                        </c:if>
                        </tbody>
                    </table>
                    </form>
                </div>
                
                <!-- Cart total-->
                <div class="row g-4 justify-content-between">
                    <div class="col-sm-8 col-md-4 col-lg-4 col-xl-4 mt-5 message_div">
                        <button name="btAction" type="submit" value="savecart" style="display: block; margin: 0 auto;" class="btn border-secondary rounded-pill px-4 py-3 text-primary saveCartBt" type="button">
                            Save cart temporary
                        </button>
                        <!-- <p class="save_message fw-normal text-primary"></p> -->
                    </div>
                    <div class="col-sm-8 col-md-7 col-lg-6 col-xl-4">
                        <div class="bg-light rounded">
                            <div class="p-4">
                                <h1 class="display-6 mb-4">Cart <span class="fw-normal">Total</span></h1>
                                <div  class="d-flex justify-content-between mb-4">
                                    <h5 style="width: 15ch;" class="mb-0 me-4">Total products:</h5>
                                    <p style="display: inline;" class="mb-0 pe-4" id="totalQuantity"><fmt:formatNumber type="number" minFractionDigits="0" maxFractionDigits="2" value="<%= (cart == null)? 0: cart.getTotalQuantity()%>"/></p>
                                </div>
                                
                                <div  class="d-flex justify-content-between mb-4">
                                    <h5 style="width: 15ch;" class="mb-0 me-4">Total price:</h5>
                                    <div class="mb-0 pe-4">$<p style="display: inline;" id="totalPrice" ><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="<%= (cart == null)? 0: cart.getTotalPrice() %>"/></p></div>
                                </div>
                                
                                <div class="d-flex justify-content-between mb-4">                                    
                                    <label for="address"><h5 class="mb-0 me-4">Deliver to:</h5></label>
                                    <select id="address" name="slAddress" class="select_field border-0 form-select-sm bg-light mb-0" data-is-sent="1">
                                        <option value="Q1">Quan 1</option>
                                        <option value="TD">Thu Duc</option>
                                        <option value="Q5">Quan 5</option>
                                        <option value="TB">Tan Binh</option>
                                    </select>
                                </div>
                                
                                <div class="d-flex justify-content-between mb-4">                                    
                                    <label for="payment_type"><h5 class="mb-0 me-4">Payment method:</h5></label>
                                    <select id="payment_type" name="slPayment" class="select_field border-0 form-select-sm bg-light mb-0" data-is-sent="1">
                                        <c:forEach var="provider" items="${providerList}">
                                            <option value="${provider.id}">${provider.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                
                                <div class="d-flex justify-content-between ">                                    
                                    <label for="shipping_type"><h5 class="mb-0 me-4">Shipping type:</h5></label>
                                    <select id="shipping_type" name="slShipType" class="select_field border-0 form-select-sm bg-light mb-0" data-is-sent="1">
                                        <c:forEach var="type" items="${shipTypeList}">
                                            <option value="${type.id}" data-price="${type.price}">${type.type}</option>
                                        </c:forEach>
                                    </select>
                                </div>  
                            </div>
                            <div class="p-4 border-top border-bottom d-flex justify-content-between">
                                <h5 class="mb-0 me-4">Total (With shipping fee)</h5>
                                <div class="mb-0 pe-4">$<p style="display: inline;" id="finalPrice" ><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="<%=(cart == null)? 20: cart.getTotalPrice() + 20 %>"/></p></div>
                                </div>
                            <button id="makeOrderBt" class="btn border-secondary rounded-pill px-4 py-3 text-primary text-uppercase m-4 ms-4" type="button" name="btAction" value="makeorder">
                                Accept
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Cart Page End -->
        
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
        
        <script src="js/MessageAppear.js" defer></script>
        <script src="js/CalculatePrice.js" defer></script>
        <script src="js/ActiveLink.js" defer></script>
<!--        <script src="js/SubmitOnReloading.js" defer></script>-->
        <script src="js/DeleteCartItemFunction.js" defer></script>
        <script src="js/SaveCartFunction.js" defer></script>
        <script src="js/MakeOrder.js" defer></script>
        
    </body>
</html>