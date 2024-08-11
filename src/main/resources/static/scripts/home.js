// const BASE_URL = "https://cc45-104-28-249-53.ngrok-free.app"
const BASE_URL = "http://localhost:8080";

window.onload = () => {
    document.getElementById('illit').href = "http://localhost:8080"
}

fetch(`${BASE_URL}/api/products`)
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json()
    })
    .then(productData => {
        productData.forEach(data => {
            const markup =
                `<li>
                <a class="item" href="${BASE_URL}/products/item?id=${data.id}">
                    <div class="image" style="background-image: url('${data.image}')"></div>
                    <div class="content">
                        <span class="product-name">${data.name}</span>
                        <div class="price">
                            <span class="new-price">${data.newPrice}</span>
                            <span class="old-price">${data.oldPrice}</span>
                        </div>
                        <div class="review">
                                <span class="rate">
                                    <i class="fa-regular fa-star"></i>
                                    <i class="fa-regular fa-star"></i>
                                    <i class="fa-regular fa-star"></i>
                                    <i class="fa-regular fa-star"></i>
                                    <i class="fa-regular fa-star"></i>
                                </span>
                            <span class="sold">${data.sold} sold</span>
                        </div>
                        <span class="location">${data.location}</span>
                    </div>
                </a>
            </li>`

            document.querySelector('ul').insertAdjacentHTML('beforeend', markup)
        })

    })
    .catch(error => {
        console.error(error)
    })



