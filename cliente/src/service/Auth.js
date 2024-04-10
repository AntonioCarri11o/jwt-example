import axios from 'axios';
import { API_URL } from '../resources/Utilities';
import { jwtDecode } from 'jwt-decode';

const generateToken = async(payload) => {
    const response = await axios.post(`${API_URL}/auth/login`, payload);
    const token = response.data.token;
    saveToken(token);
}

const logOut = () => {
    localStorage.removeItem('token');
    localStorage.removeItem('role');    
}

const returnToken = () => {
    return localStorage.getItem('token');
}

const saveToken = (token) => {
    const decoded = jwtDecode(token);
    const role = decoded.ROLES[0].authority;
    localStorage.setItem('token', token);
    localStorage.setItem('role', role);
}

const returnRole = () => {
    return localStorage.getItem('role');
}

const clearAuth = () => {
    localStorage.removeItem('token');
    localStorage.removeItem('role');
}
export default {
    generateToken,
    returnToken,
    returnRole,
    clearAuth,
    logOut,
}