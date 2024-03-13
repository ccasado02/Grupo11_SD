window.addEventListener('scroll', function() {
    var navbar = document.querySelector('.head');
    var originalSearchContainer = document.querySelector('.container .search-container');
    var headSearchContainer = document.querySelector('.head .head-search');

    if (window.scrollY > originalSearchContainer.offsetTop + originalSearchContainer.offsetHeight-75) {
        headSearchContainer.classList.add('show-search');
    } else {
        headSearchContainer.classList.remove('show-search');
    }
});