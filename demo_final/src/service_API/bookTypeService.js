import request from '../config/http'

const findAll = () => {
    return request.get(`/bookTypes`)
}

const bookTypeService = {
    findAll
}

export default bookTypeService