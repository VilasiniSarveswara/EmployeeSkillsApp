import axios from 'axios';


const http = axios.create({
    baseURL: "http://localhost:8080"
})

export default{
    listAllEmployees(){
        return http.get('/employees');
    },

    listEmployee(id){
        return http.get(`/employees/${id}`)
    },

    createEmployee(employee){
        return http.post('/employees', employee)
    },

    updateEmployee(id,employee){
        return http.put(`/employees/${id}`, employee)
    },
    deleteEmployee(id,employee){
        return http.delete(`/employees/${id}`, employee)
    },
}