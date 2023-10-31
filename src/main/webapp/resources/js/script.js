(function ($) {

  "use strict";

  $(document).ready(function () {
    $('.navbar').on('click', '.search-toggle', function (e) {
      var selector = $(this).data('selector');

      $(selector).toggleClass('show').find('.search-input').focus();
      $(this).toggleClass('active');

      e.preventDefault();
    });

    // close when click off of container
    $(document).on('click touchstart', function (e) {
      if (!$(e.target).is('.search-toggle, .search-toggle *, .navbar, .navbar *')) {
        $('.search-toggle').removeClass('active');
        $('.navbar').removeClass('show');
      }
    });

    $("#openbasket").on('click', function() {
      $("#basketList").css('visibility','visible');
    });

    $("#closebasket").on('click', function() {
      $("#basketList").css('visibility','visible');
    });

    // Responsive Navigation with Button
    var initHamburgerMenu = function () {
      const hamburger = document.querySelector(".hamburger");
      const navMenu = document.querySelector(".menu-list");
      const navLink = document.querySelectorAll(".nav-link");

      hamburger.addEventListener("click", mobileMenu);
      function mobileMenu() {
        hamburger.classList.toggle("active");
        navMenu.classList.toggle("responsive");
      }

      navLink.forEach(n => n.addEventListener("click", closeMenu));

      function closeMenu() {
        hamburger.classList.remove("active");
        navMenu.classList.remove("responsive");
      }
    };

    // Payment method
    $('input[type="radio"]').click(function () {
      var inputValue = $(this).attr("value");
      var targetBox = $("." + inputValue);
      $(".payment-box").not(targetBox).hide();
      $(targetBox).show();
    });

    // document ready
    $(document).ready(function () {
      initHamburgerMenu();
    });
  });
})(jQuery);