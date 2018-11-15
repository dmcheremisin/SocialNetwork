<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Index</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">

</head>
<body>
<div class="navbar navbar-custom navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="./">Social Network</a>
        </div>
        <div class="navbar-collapse collapse">
            <form class="navbar-form navbar-right">
                <div class="form-group">
                    <input type="text" placeholder="Email" class="form-control" >
                </div>
                <div class="form-group">
                    <input type="password" placeholder="Password" class="form-control" >
                </div>
                <button type="submit" class="btn btn-success">Вход</button>
            </form>
            <div class="navbar-right">
                <a href="#" class="navbar-brand">Ru</a>
                <a href="#" class="navbar-brand">En</a>
            </div>
        </div>
    </div>
</div>
<div class="jumbotron">
    <div class="container">
        <h1>Social network facilities:</h1>
        <ul class="list-inline">
            <li>Registration</li>
            <li>Authorization</li>
            <li>Profile view and change</li>
            <li>User search</li>
            <li>Friends</li>
            <li>Messages</li>
        </ul>
        <hr />
        <p>Join social network and become a member with full capabilities!</p>
    </div>
</div>
<div class="container">
    <div class="row">
        <div class="col-md-4">
        </div>
        <div class="col-md-4">
            <h1>Register today!</h1>
            <div class="form-group">
                <form action="/register" method="post">
                    <div class="form-group">
                        <label for="email">Email: <input type="text" name="email" class="form-control" id="email" /></label>
                    </div>
                    <div class="form-group">
                        <label for="password">Password: <input type="password" name="password" class="form-control" id="password" /></label>
                    </div>
                    <div class="form-group">
                        <label for="conf-password">Confirm Password: <input type="password" name="password-confirm" class="form-control" id="conf-password" /></label>
                    </div>
                    <div class="form-group">
                        <input type="submit" name="submit" value="Register &raquo" class="btn btn-success" /></label>
                    </div>
                </form>
            </div>
        </div>
        <div class="col-md-4">
        </div>
    </div>
    <hr />
    <footer>
        <p>&copy All rights reserved.</p>
    </footer>
</div>

<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>