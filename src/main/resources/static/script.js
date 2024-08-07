fetch("http://localhost:8080/api/products/1")
.then(response => {
    if (!response.ok) {
        throw new Error('Network response was not ok');
    }
    return response.json()
})
.then(productData => {
    console.log(productData.data)
    const markup =
        `
        <li>
        <a class="item" href="">
            <div class="image" style="background-image: url('${productData.data.image}')"></div>
            <div class="content">
                <span class="product-name">${productData.data.name}</span>
                <div class="price">
                    <span class="new-price">${productData.data.newPrice}</span>
                    <span class="old-price">${productData.data.oldPrice}</span>
                </div>
                <div class="review">
                        <span class="rate">
                            <i class="fa-regular fa-star"></i>
                            <i class="fa-regular fa-star"></i>
                            <i class="fa-regular fa-star"></i>
                            <i class="fa-regular fa-star"></i>
                            <i class="fa-regular fa-star"></i>
                        </span>
                    <span class="sold">0 sold</span>
                </div>
                <span class="location">${productData.data.location}</span>
            </div>
        </a>
    </li>
        `
    document.querySelector('ul').insertAdjacentHTML('beforeend',markup)
})
.catch(error => {
    console.error(error)
})