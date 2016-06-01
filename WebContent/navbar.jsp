<%@page import="util.MD5Util"%>
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <img src="images/bullhornlogo50x50.png" alt="Bullhorn Logo"/>&nbsp;<h2>Bullhorn</h2>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
    
    
      <ul class="nav navbar-nav">
        <li class="active"><a href="home.jsp">Home<span class="sr-only">(current)</span></a></li>
        <li><a href="Newsfeed">News Feed</a></li>
        <!--  <li><a href="advancedsearch.jsp">Advanced Search</a></li>-->
       <!-- 
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#">Action</a></li>
            <li><a href="#">Another action</a></li>
            <li><a href="#">Something else here</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="#">Separated link</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="#">One more separated link</a></li>
          </ul>
        </li>
        -->
        
      </ul>
    
      <form class="navbar-form navbar-right" role="search" action="Newsfeed" method="get">
        <div class="form-group">
          <input type="text" class="form-control" placeholder="Search" name="searchtext">
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
      </form>
    
      <ul class="nav navbar-nav navbar-right">
      <% if (session.getAttribute("user") != null) { %>
        <li><a href="ProfileServlet?userid=${user.bhuserid}&action=viewprofile"><img alt="${user.username}" src="${gravatarURL}"/>&nbsp;${user.username}</a></li>
      <% } %>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">User Options <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <!--  <li><a href="LoginServlet?action=logout">Logout</a></li>-->
            
            <li>
              <!-- Bootstrap allows me to put a form here and it will show in the navbar.
                   I want to use a form so it can call the servlet with the Post method.              
               -->
               <form class="navbar-form navbar-left" role="form" action="LoginServlet" method="post">
                  <input type="hidden" name="action" id="action" value="logout"/>
                  <button class="btn btn-default" id="addBookButton">Logout</button>        
               </form>
            </li>
            <li><a href="ProfileServlet?userid=${user.bhuserid }&action=editprofile">Edit Profile</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="support.jsp">Feedback</a></li>
          </ul>
        </li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>