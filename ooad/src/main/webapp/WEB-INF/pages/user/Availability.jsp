<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ETrains</title>
<link rel="stylesheet" href="UserHome_Css.css">
<style>
</style>
</head>
<body>
    <header>
        <h1 class="hd">National Ticket Booking Spot</h1>
        <div class="home">
            <p class="menu">
                <a href="userhome.jsp">Home</a>
            </p>
        </div>
        <div class="home">
            <p class="menu">
                <a href="userviewtrainfwd.jsp">View Trains</a>
            </p>
        </div>
        <div class="home">
            <p class="menu">
                <a href="trainbwstnfwd.jsp">Trains Between Stations</a>
            </p>
        </div>
        <div class="home">
            <p class="menu">
                <a href="bookingdetails.jsp">Ticket Booking History</a>
            </p>
        </div>
        <div class="home">
            <p class="menu">
                <a href="fareenqfwd.jsp">Fare Enquiry</a>
            </p>
        </div>
        <div class="home">
            <p class="menu">
                <a href="useravailfwd.jsp">Seat Availability</a>
            </p>
        </div>
        <div class="home">
            <p class="menu">
                <a href="usersearchtrain.jsp">Search By TrainNo</a>
            </p>
        </div>
        <div class="home">
            <p class="menu">
                <a href="userprofile.jsp">Profile</a>
            </p>
        </div>
        <div class="home">
            <p class="menu">
                <a href="userlogout.jsp">Logout</a>
            </p>
        </div>
    </header>

    <div class="tab">
        <p class="menu">Seat Availability</p>
    </div>
    <form action="useravail.jsp" class="tab red" method="post">
        TrainNumber: <input type="text" name="trainno"><br /> <br />
        <input type="submit" value=" SEARCH TRAIN AVAILABILITY"><br />
    </form>
    <br />

</body>
</html>
