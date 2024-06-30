import React from "react";
import { useEffect } from "react";
import { useState } from "react";
import bookService from "../service_API/bookService";
import bookTypeService from "../service_API/bookTypeService";
import { Link } from "react-router-dom";
import ReactPaginate from "react-paginate";
import { Form, Formik, Field } from "formik";
import ModalDelete from "../util/ModalDelete";
import { toast } from "react-toastify";

function Book() {
  const [books, setBooks] = useState([]);
  const [bookTypes, setBookTypes] = useState([]);
  const [pageCount, setPageCount] = useState(0);
  const [filters, setFilters] = useState({
    page: 0,
    name: "",
    type: "",
  });
  const [deletedObject, setDeletedObject] = useState({
    deletedId: "",
    deletedName: "",
  });

  const handleTransferInfo = (deletedObject) => {
    setDeletedObject((prev) => ({ ...prev, ...deletedObject }));
  };

  const handleDelete = async () => {
    try {
      await bookService.remove(deletedObject.deletedId);
      const newFilters = {...filters}
      setFilters(newFilters)
      toast("Xóa thành công");
    } catch (error) {
      console.warn(error);
      toast("Xóa thất bại");
    }
  };

  const handlePageClick = (event) => {
    setFilters((prev) => ({ ...prev, page: event.selected }));
  };

  useEffect(() => {
    const getBookTypes = async () => {
      const bookTypesData = await bookTypeService.findAll();
      setBookTypes(bookTypesData.data);
    };

    getBookTypes();
  }, []);

  useEffect(() => {
    const getBooks = async () => {
      try {
        const response = await bookService.findAllWithPageOrName(filters);
        setBooks(response.data.content);
        setPageCount(response.data.totalPages);
      } catch (error) {
        console.warn(error);
      }
    };

    getBooks();
  }, [filters]);

  return (
    <>
      <Link to="/create" className="btn btn-success mb-3">
        Thêm thông báo
      </Link>

      <Formik
        initialValues={{
          name: filters.name,
          type: filters.type,
        }}
        onSubmit={(values) => {
          setFilters((prev) => {
            return { ...prev, ...values, page: 0 };
          });
        }}
      >
        <Form>
          <Field name="name" style={{ height: "34px" }} />
          <Field as="select" style={{ height: "34px" }} name="type">
            <option value="">--- Hãy chọn tên thông báo ---</option>
            {bookTypes.map((type) => (
              <option key={type.id} value={type.id}>
                {type.name}
              </option>
            ))}
          </Field>
          <button type="submit" className="btn btn-secondary me-3">
            Tìm kiếm
          </button>
          <div id="error" className="text-danger" style={{ display: "none" }}>
            Không tìm thấy sách
          </div>
        </Form>
      </Formik>

      <table className="table table-striped">
        <thead>
          <tr>
            <th scope="col">STT</th>
            <th scope="col">Mã thông báo</th>
            <th scope="col">Tên thông báo</th>
            <th scope="col">Chi tiết thông báo</th>
            <th scope="col">Ngày nhập thông báo</th>
            <th scope="col">Chức năng</th>
          </tr>
        </thead>
        <tbody>
          {books.map((book, index) => (
            <tr key={index}>
              <th scope="row">{++index}</th>
              <td>{book.code}</td>
              <td>{book.name}</td>
              <td>
                {
                  bookTypes.filter((type) => type.id === book.bookTypeDTO.id)[0]
                    ?.name
                }
              </td>
              <td>{book.importedDate}</td>
              <td>
                <Link to={`/edit/${book.id}`} className="btn btn-primary me-3" >
                  Sửa
                </Link>
                <button
                  type="button"
                  className="btn btn-danger"
                  data-bs-toggle="modal"
                  data-bs-target="#exampleModal"
                  onClick={() =>
                    handleTransferInfo({
                      deletedId: book.id,
                      deletedName: book.name,
                    })
                  }
                >
                  Delete
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>

      <ModalDelete
        deletedName={deletedObject.deletedName}
        onCompletedDelete={handleDelete}
      />

      <div className="d-grid">
        <ReactPaginate
          breakLabel="..."
          nextLabel=">"
          onPageChange={handlePageClick}
          pageCount={pageCount}
          previousLabel="< "
          containerClassName="pagination"
          pageLinkClassName="page-num"
          nextLinkClassName="page-next"
          previousLinkClassName="page-previous"
          activeClassName="active"
          disabledClassName="d-none"
        />
      </div>
    </>
  );
}

export default Book;
