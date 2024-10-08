import {BASE_URL} from './const.js'

const url = new URLSearchParams(window.location.search)

fetch(`${BASE_URL}/api/products/${url.get('id')}`)
    .then(response => {
        if (!response.ok) {
            throw new Error(response.statusText);
        }
        return response.json()
    })
    .then(productData => {
        const product = productData.data
        console.log(productData)
        const mainProductImage = document.getElementById('main-product-image-id')
        const productName = document.getElementById('product-name-id')
        const oldPrice = document.getElementById('old-price-id')
        const newPrice = document.getElementById('new-price-id')
        const availableAmount = document.getElementById('product-available-amount')

        mainProductImage.style.backgroundImage = `url('${product.image}')`
        productName.innerHTML = product.name
        oldPrice.innerHTML = product.oldPrice
        newPrice.innerHTML = product.newPrice
        availableAmount.innerHTML = product.stock
    })
    .catch(error => {
        console.log(error)
    })

