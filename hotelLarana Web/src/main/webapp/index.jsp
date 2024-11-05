<%@ page import="javax.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html data-bs-theme="light" lang="en">
  <head>
    <meta charset="utf-8" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1.0, shrink-to-fit=no"
    />
    
    <link rel="icon" href="imgs/favicon-black.png" type="image/png">
    <title>Larana Hotel | Home</title>
    <link rel="stylesheet" href="./index-assets/assets/bootstrap/css/bootstrap.min.css" />
    <link
      rel="stylesheet"
      href="https://fonts.googleapis.com/css?family=Roboto+Slab:300,400|Roboto:300,400,700"
    />
    <link rel="stylesheet" href="./index-assets/assets/css/Black-Navbar.css" />
    <link rel="stylesheet" href="./index-assets/assets/css/Carousel-Hero.css" />
    <link
      rel="stylesheet"
      href="./index-assets/assets/css/Customizable-Carousel-swipe-enabled.css"
    />
    <link rel="stylesheet" href="./index-assets/assets/css/Features-Image-images.css" />
    <link rel="stylesheet" href="./index-assets/assets/css/Navbar-With-Button-icons.css" />
  </head>

  <body
    style="
      margin-bottom: 20px;
      border-width: 1px;
      border-top-width: 1px;
      border-right-width: 1px;
      border-bottom-width: 1px;
      border-left-width: 1px;
      border-left-style: dashed;
    "
  >
    <header>
      <nav
        class="navbar navbar-expand-md py-3 navbar-light"
        style="
          padding: 0px;
          padding-bottom: 0px;
          padding-top: 0px;
          margin-right: 5%;
          margin-left: 5%;
        "
      >
        <div class="container">
          <a class="navbar-brand d-flex align-items-center" href="#"
            ><img
              src="./index-assets/assets/img/websitePrimLogoWide.png"
              style="width: 150px" /></a
          ><button
            data-bs-toggle="collapse"
            class="navbar-toggler"
            data-bs-target="#navcol-3"
          >
            <span class="visually-hidden">Toggle navigation</span
            ><span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="navcol-3">
            <ul class="navbar-nav mx-auto">
              <li class="nav-item">
                <a class="nav-link active" href="#home" style="color: #58490f"
                  >Home</a
                >
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#rooms" style="color: #58490f">Rooms</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#services" style="color: #58490f">Services</a>
              </li>
            </ul>
            
            
            
            
            <%
            String Email = (String) session.getAttribute("Email");
            if (Email != null) { %>
            <button
              class="btn btn-primary"
              type="button"
              style="
                background: rgba(88, 73, 15, 0);
                border-radius: 8px;
                border-width: 2px;
                border-color: #58490f;
                color: #58490f;
              "
              onclick="window.location.href='index.jsp';"
            >
              Logout</button>
        <% } else { %>
            <button
              class="btn btn-primary"
              type="button"
              style="
                background: rgba(88, 73, 15, 0);
                border-radius: 8px;
                border-width: 2px;
                border-color: #58490f;
                color: #58490f;
              "
              onclick="window.location.href='login.jsp';"
            >
              Login</button>
        <% } %>
            
            
            
            
              
              
              
              
              
              
              
              
              
              
              
              
              
              
              
              <button
              class="btn btn-primary"
              type="button"
              style="
                background: rgb(88, 73, 15);
                border-radius: 8px;
                margin-left: 5px;
                border-color: #58490f;
              "
              onclick="window.location.href='booking.jsp';"
            >
              Book Now
            </button>
          </div>
        </div>
      </nav>
    </header>
    <div
      id="carouselExampleCaptions"
      class="carousel slide"
      style="margin-right: 5%; margin-left: 5%"
    >
      <div class="carousel-indicators">
        <button
          class="active"
          type="button"
          data-bs-target="#carouselExampleCaptions"
          data-bs-slide-to="0"
          aria-current="true"
          aria-label="Slide 1"
        ></button
        ><button
          type="button"
          data-bs-target="#carouselExampleCaptions"
          data-bs-slide-to="1"
          aria-label="Slide 2"
        ></button
        ><button
          type="button"
          data-bs-target="#carouselExampleCaptions"
          data-bs-slide-to="2"
          aria-label="Slide 3"
        ></button>
      </div>
      <div class="carousel-inner">
        <div
          class="carousel-item active"
          style="
            display: flex;
            display: flex;
            justify-content: center;
            align-items: center;
          "
        >
          <img
            class="d-block w-100"
            src="./index-assets/assets/img/hotel-pool3.jpg"
            alt="..."
            style="border-radius: 8px; height: auto; width: 100%"
          />
          <div class="d-none d-md-block carousel-caption">
            <h3 style="width: 300; text-shadow: 0px 0px 8px">
              Dive into Luxury
            </h3>
            <p style="text-shadow: 0px 0px 8px">
              Immerse yourself in the epitome of opulence with our breathtaking
              poolside paradise.
            </p>
            <button
              class="btn btn-primary"
              type="button"
              style="
                border-radius: 20px;
                color: var(--bs-btn-disabled-color);
                background: rgba(47, 52, 56, 0);
                backdrop-filter: blur(11px);
                border-width: 1px;
                border-color: var(--bs-btn-disabled-color);
                border-top-width: 2px;
                border-right-width: 2px;
                border-bottom-width: 2px;
                border-left-width: 2px;
              "
              onclick="window.location.href='booking.jsp';"
            >
              Book Now
            </button>
          </div>
        </div>
        <div class="carousel-item">
          <img
            class="d-block w-100"
            src="./index-assets/assets/img/hotel-food3.jpg"
            alt="..."
            style="border-radius: 8px"
          />
          <div class="d-none d-md-block carousel-caption">
            <h5 style="text-shadow: 0px 0px 8px">Culinary Delights</h5>
            <p style="text-shadow: 0px 0px 8px">
              Elevate your senses with a culinary journey at LARANA's exquisite
              restaurant
            </p>
            <button
              class="btn btn-primary"
              type="button"
              style="
                border-radius: 20px;
                color: var(--bs-btn-disabled-color);
                background: rgba(47, 52, 56, 0);
                backdrop-filter: blur(11px);
                border-width: 1px;
                border-color: var(--bs-btn-disabled-color);
                border-top-width: 2px;
                border-right-width: 2px;
                border-bottom-width: 2px;
                border-left-width: 2px;
              "
              onclick="window.location.href='booking.jsp';"
            >
              Book Now
            </button>
          </div>
        </div>
        <div class="carousel-item">
          <img
            class="d-block w-100"
            src="./index-assets/assets/img/hotel-suite2.jpg"
            alt="..."
            style="border-radius: 8px"
          />
          <div class="d-none d-md-block carousel-caption">
            <h5 style="text-shadow: 0px 0px 8px">Sweet Dreams in Luxury</h5>
            <p style="text-shadow: 0px 0px 8px">
              Retreat to the epitome of comfort and sophistication with our
              plush hotel beds.
            </p>
            <button
              class="btn btn-primary"
              type="button"
              style="
                border-radius: 20px;
                color: var(--bs-btn-disabled-color);
                background: rgba(47, 52, 56, 0);
                backdrop-filter: blur(11px);
                border-width: 1px;
                border-color: var(--bs-btn-disabled-color);
                border-top-width: 2px;
                border-right-width: 2px;
                border-bottom-width: 2px;
                border-left-width: 2px;
              "
              onclick="window.location.href='booking.jsp';"
            >
              Book Now
            </button>
          </div>
        </div>
      </div>
      <button
        class="carousel-control-prev"
        type="button"
        data-bs-target="#carouselExampleCaptions"
        data-bs-slide="prev"
      >
        <span class="carousel-control-prev-icon" aria-hidden="true"></span
        ><span class="visually-hidden">Previous</span></button
      ><button
        class="carousel-control-next"
        type="button"
        data-bs-target="#carouselExampleCaptions"
        data-bs-slide="next"
      >
        <span class="carousel-control-next-icon" aria-hidden="true"></span
        ><span class="visually-hidden">Next</span>
      </button>
    </div>
    <div id="rooms" class="container py-4 py-xl-5">
      <h1
        style="
          text-align: center;
          color: #58490f;
          padding-bottom: 1px;
          margin-bottom: 24px;
          border-width: 1px;
          border-color: var(--bs-body-color);
          border-top-width: 1px;
          border-top-color: var(--bs-body-color);
        "
      >
        Our Rooms
      </h1>
      <div class="row row-cols-1 row-cols-md-2">
        <div class="col">
          <img
            class="rounded w-100 h-100 fit-cover"
            style="min-height: 300px"
            src="./index-assets/assets/img/hotel-suite1.jpg"
          />
        </div>
        <div class="col d-flex flex-column justify-content-center p-4">
          <div
            class="text-center text-md-start d-flex flex-column align-items-center align-items-md-start mb-5"
          >
            <div>
              <h4>Suite Room</h4>
              <p>
                Indulge in the ultimate luxury experience with our spacious and
                elegantly designed Suite Rooms. Enjoy breathtaking views from
                large windows, unwind in a separate living area, and sleep in a
                king-size bed. The Suite Room is perfect for those seeking a
                lavish retreat.
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="container py-4 py-xl-5">
      <div class="row row-cols-1 row-cols-md-2">
        
        <div class="col d-flex flex-column justify-content-center p-4">
          <div
            class="text-center text-md-start d-flex flex-column align-items-center align-items-md-start mb-5"
          >
            <div>
              <h4>Standard Room</h4>
              <p>
                Experience comfort and convenience in our Standard Rooms. These
                cozy rooms are well-appointed with modern amenities and feature
                a queen-size bed. Whether you are traveling for business or
                leisure, the Standard Room provides a welcoming and relaxing
                environment for your stay.
              </p>
            </div>
          </div>
        </div>
        <div class="col">
            <div>
              <div></div>
            </div>
            <img
              class="rounded w-100 h-100 fit-cover"
              style="min-height: 300px"
              src="./index-assets/assets/img/hotel-standard2.jpg"
            />
          </div>
      </div>
    </div>
    <div id="services" class="container py-4 py-xl-5">
      <div class="row mb-5">
        <div class="col-md-8 col-xl-6 text-center mx-auto" style="width: 600px">
          <h2 style="color: #58490f; border-top-color: var(--bs-body-color)">
            Experience LARANA Hotel
          </h2>
          <p>
            Discover the unparalleled hospitality and amenities offered at
            LARANA Hotel. Our commitment to excellence ensures a memorable stay
            for every guest. Here are some of the outstanding features
          </p>
        </div>
      </div>
      <div class="row gy-4 row-cols-1 row-cols-md-2 row-cols-xl-3">
        <div class="col">
          <div class="card">
            <div
              class="card-body p-4"
              style="height: 400px; text-align: center"
            >
              <img
                width="237"
                height="189"
                src="./index-assets/assets/img/hotel-spa.jpg"
                style="border-radius: 8px; width: 250px; text-align: center"
              />
              <h4 class="card-title" style="margin-top: 20px">Spa Serenity</h4>
              <p class="card-text" style="margin-bottom: 10px">
                Indulge in relaxation at our spa oasis. Let the stress melt away
                with rejuvenating treatments and massages, leaving you refreshed
                and revitalized.
              </p>
            </div>
          </div>
        </div>
        <div class="col">
          <div class="card">
            <div
              class="card-body p-4"
              style="height: 400px; text-align: center"
            >
              <img
                width="242"
                height="193"
                src="./index-assets/assets/img/hotel-gym.jpg"
                style="border-radius: 8px; width: 250px"
              />
              <h4 class="card-title" style="margin-top: 20px">
                Modern Gym Facilities
              </h4>
              <p class="card-text" style="margin-bottom: 10px">
                Elevate your fitness routine in our state-of-the-art gym
                equipped with the latest technology and a variety of exercise
                options.
              </p>
            </div>
          </div>
        </div>
        <div class="col">
          <div class="card">
            <div
              class="card-body p-4"
              style="height: 400px; text-align: center"
            >
              <img
                width="250"
                height="200"
                src="./index-assets/assets/img/hotel-event.jpg"
                style="border-radius: 8px"
              />
              <h4 class="card-title" style="margin-top: 20px">
                Event Elegance
              </h4>
              <p class="card-text" style="margin-bottom: 10px">
                Host your special moments in our elegant event spaces. Whether
                it's a celebration or a business gathering, our venues set the
                stage for unforgettable experiences.
              </p>
            </div>
          </div>
        </div>
        <div class="col">
          <div class="card">
            <div
              class="card-body p-4"
              style="height: 400px; text-align: center"
            >
              <img
                width="250"
                height="200"
                src="./index-assets/assets/img/hotel-restau2.jpg"
                style="border-radius: 8px"
              />
              <h4 class="card-title" style="margin-top: 20px">
                Culinary Delights
              </h4>
              <p class="card-text" style="margin-bottom: 10px">
                Embark on a gastronomic journey at our diverse dining
                establishments. From gourmet dishes to local favorites, our
                culinary offerings cater to every palate.
              </p>
            </div>
          </div>
        </div>
        <div class="col">
          <div class="card">
            <div
              class="card-body p-4"
              style="height: 400px; text-align: center"
            >
              <img
                width="250"
                height="200"
                src="./index-assets/assets/img/hotel-staff.jpg"
                style="border-radius: 8px"
              />
              <h4 class="card-title" style="margin-top: 20px">
                Dedicated Hosts
              </h4>
              <p class="card-text">
                More than a team, our staff are dedicated hosts, ready to assist
                with any request, offering personalized recommendations, and
                ensuring your comfort throughout your stay.
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
    <footer class="text-center bg-dark" style="margin-top: 20px">
      <div class="container text-white py-4 py-lg-5">
        <p class="text mb-0">Copyright © 2023 LARANA Hotel</p>
      </div>
    </footer>
    <script type="text/javascript"> 
		function logout(){
				<%session.invalidate();%>
				window.location.href='index.jsp';
			}
    </script>
    <script src="./index-assets/assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="./index-assets/assets/js/Customizable-Carousel-swipe-enabled-jquery.touchSwipe.min.js"></script>
  </body>
</html>
