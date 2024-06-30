import request from '../config/http'

const findAll = () => {
    return request.get(`/books`)
}

const save = (book) => {
    return request.post(`/books`, {...book})
}

const findBynameOrType = (name, type) => {
    return request.get(`/books?name_like=${name}&typeId_like=${type}`)
} 

const bookService = {
    findAll,
    save,
    findBynameOrType
}

export default bookService