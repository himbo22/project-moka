import {BASE_URL} from './const.js'

window.onload = () => {
    document.getElementById('illit').href = BASE_URL
    document.getElementById('user-avatar').href = BASE_URL + "/profile"
}