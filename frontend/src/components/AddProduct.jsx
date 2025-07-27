import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate } from 'react-router-dom';
import { toast } from "react-toastify";
import productvalidation from "./productvalidation";
import swal from 'sweetalert';
import { useDispatch } from "react-redux";

function AddProduct() {

    const sellerid = sessionStorage.getItem("id")
    const [product, setProduct] = useState({
        "pname": "",
        "categoryName": "",
        "price": "",
        "brand": "",
        "sellerId": sellerid
    })
    const dispatch = useDispatch()
    const [errors, setErrors] = useState({})
    const [selectedPhoto, setSelectedPhoto] = useState("")
    const [file, setFile] = useState(null)
    const [submitted, setSubmitted] = useState(false)

    const [category, setCategory] = useState([])
    const navigate = useNavigate();

    const handleInput = e => {
        setProduct({ ...product, [e.target.name]: e.target.value })
    }
    const handleInputImage =e=> {
        setSelectedPhoto(e.target.files[0]);
      };
    const handleFileInput = e => {
        // setSelectedPhoto(e.target.files[0])
        setFile(URL.createObjectURL(e.target.files[0]))
    }
    window.onbeforeunload = function () {
        sessionStorage.setItem("origin", window.location.href);
    }

    window.onload = function () {
        if (window.location.href == sessionStorage.getItem("origin")) {
            dispatch({ type: 'IsLoggedIn' })
        }
    }

    const handleSubmit = e => {
        e.preventDefault()
        setErrors(productvalidation(product))
        setSubmitted(true)
    }

    useEffect(() => {

        axios.get("http://localhost:8080/api/category/getallcategory")
            .then(resp => {
                console.log(resp);
                setCategory(resp.data);
                console.log("GetAllCategory");
            }).catch(error => {
                toast.error("Category unable to fetch")
            }, [])

        console.log(Object.keys(errors).length)
        if (Object.keys(errors).length === 0 && submitted) {
            const formData = new FormData()
            formData.append("pic", selectedPhoto)
            formData.append("pname", product.pname)
            formData.append("categoryName", product.categoryName)
            formData.append("price", product.price)
            formData.append("brand", product.brand)
            formData.append("sellerId", sellerid)
            console.log(product)
            axios.post("http://localhost:8080/api/products", formData)
                .then(resp => {
                    let result = resp.data.data;
                    console.log(result)
                    swal({
                        title: "Done!",
                        text: "Product saved successfully",
                        icon: "success",
                        button: "OK",
                    });
                    navigate('/myproducts');
                })
                .catch(error => {
                    console.log("Error", error);
                    toast.error("Error saving product")
                    swal({
                        title: "Error!",
                        text: "Error saving product",
                        icon: "error",
                        button: "OK",
                    });
                })
        }
    }, [errors])
    return (
        <div className="container mt-5">
            <div className="card shadow bg-transparent text-dark">
                <div className="card-body">
                    <div className="row">
                        {/* <div class="col-sm-4 pt-5">
                            {selectedPhoto ? <img className="img-thumbnail" src={file} alt="Photo" /> : ""}
                        </div> */}
                        <div className="col-4 ">
                    <img
                        src="https://cdn-icons-png.flaticon.com/512/7245/7245845.png"
                        className="rounded-start img-fluid mb-3"
                        style={{ width: "100 x" }} alt="category" />
                </div>
                        <div className="col-sm-6">
                            <h4 className="text-center p-2">
                                Add Product Form
                            </h4>
                            <form onSubmit={handleSubmit}>
                                <div className="form-group form-row">
                                    <label className="col-sm-4 form-control-label">Product Name</label>
                                    <div className="col-sm-8">
                                        <input type="text" name="pname" value={product.pname} onChange={handleInput} className="form-control" />
                                        {errors.pname && <medium className="text-danger float-right">{errors.pname}</medium>}
                                    </div>
                                </div>
                                <div className="form-group form-row">
                                    <label className="col-sm-4 form-control-label">Category</label>
                                    <div className="col-sm-8">
                                        <select name="categoryName" value={product.categoryName} onChange={handleInput} className="form-control">
                                            <option value="">please select</option>
                                            {category.map(cat => (
                                                <option>{cat.categoryName}</option>
                                            ))}
                                        </select>
                                        {errors.categoryName && <medium className="text-danger float-right">{errors.categoryName}</medium>}
                                    </div>
                                </div>
                                <div className="form-group form-row">
                                    <label className="col-sm-4 form-control-label">Price</label>
                                    <div className="col-sm-8">
                                        <input type="number" name="price" value={product.price} onChange={handleInput} className="form-control" />
                                        {errors.price && <medium className="text-danger float-right">{errors.price}</medium>}
                                    </div>
                                </div>
                                <div className="form-group form-row">
                                    <label className="col-sm-4 form-control-label">Brand</label>
                                    <div className="col-sm-8">
    <select name="brand" value={product.brand} onChange={handleInput} className="form-control">
        <option value="">Select Vehicle Brand</option>

        {/* Cars - India */}
        <optgroup label="🚗 India (Car)">
            <option value="Tata Motors">Tata Motors</option>
            <option value="Mahindra & Mahindra">Mahindra & Mahindra</option>
            <option value="Maruti Suzuki">Maruti Suzuki</option>
            <option value="Hyundai Motor India">Hyundai Motor India</option>
            <option value="Honda Cars India">Honda Cars India</option>
            <option value="Toyota Kirloskar Motor">Toyota Kirloskar Motor</option>
            <option value="Kia Motors India">Kia Motors India</option>
            <option value="Skoda Auto India">Skoda Auto India</option>
            <option value="Renault India">Renault India</option>
            <option value="MG Motor India">MG Motor India</option>
            <option value="Volkswagen India">Volkswagen India</option>
            <option value="Nissan Motor India">Nissan Motor India</option>
            <option value="Citroën India">Citroën India</option>
            <option value="BYD India">BYD India</option>
        </optgroup>

        {/* Cars - Japan */}
        <optgroup label="🚗 Japan (Car)">
            <option value="Toyota">Toyota</option>
            <option value="Honda">Honda</option>
            <option value="Nissan">Nissan</option>
            <option value="Suzuki">Suzuki</option>
            <option value="Mazda">Mazda</option>
            <option value="Subaru">Subaru</option>
            <option value="Mitsubishi">Mitsubishi</option>
            <option value="Lexus">Lexus</option>
            <option value="Daihatsu">Daihatsu</option>
        </optgroup>

        {/* Cars - USA */}
        <optgroup label="🚗 USA (Car)">
            <option value="Ford">Ford</option>
            <option value="General Motors">General Motors</option>
            <option value="Tesla">Tesla</option>
            <option value="Chrysler">Chrysler</option>
            <option value="Dodge">Dodge</option>
            <option value="Jeep">Jeep</option>
        </optgroup>

        {/* Cars - Germany */}
        <optgroup label="🚗 Germany (Car)">
            <option value="BMW">BMW</option>
            <option value="Mercedes-Benz">Mercedes-Benz</option>
            <option value="Audi">Audi</option>
            <option value="Volkswagen">Volkswagen</option>
            <option value="Porsche">Porsche</option>
            <option value="Opel">Opel</option>
        </optgroup>

        {/* Cars - South Korea */}
        <optgroup label="🚗 South Korea (Car)">
            <option value="Hyundai">Hyundai</option>
            <option value="Kia">Kia</option>
            <option value="Genesis">Genesis</option>
        </optgroup>

        {/* Cars - China */}
        <optgroup label="🚗 China (Car)">
            <option value="BYD">BYD</option>
            <option value="Geely">Geely</option>
            <option value="Great Wall Motors">Great Wall Motors</option>
            <option value="NIO">NIO</option>
            <option value="Xpeng">Xpeng</option>
            <option value="Changan Automobile">Changan Automobile</option>
            <option value="SAIC Motor">SAIC Motor</option>
        </optgroup>

        {/* Cars - France */}
        <optgroup label="🚗 France (Car)">
            <option value="Peugeot">Peugeot</option>
            <option value="Citroën">Citroën</option>
            <option value="Renault">Renault</option>
            <option value="DS Automobiles">DS Automobiles</option>
        </optgroup>

        {/* Cars - UK */}
        <optgroup label="🚗 UK (Car)">
            <option value="Land Rover">Land Rover</option>
            <option value="Jaguar">Jaguar</option>
            <option value="Aston Martin">Aston Martin</option>
            <option value="Bentley">Bentley</option>
            <option value="Rolls-Royce">Rolls-Royce</option>
            <option value="Mini">Mini</option>
            <option value="McLaren">McLaren</option>
        </optgroup>

        {/* Cars - Italy */}
        <optgroup label="🚗 Italy (Car)">
            <option value="Ferrari">Ferrari</option>
            <option value="Lamborghini">Lamborghini</option>
            <option value="Maserati">Maserati</option>
            <option value="Fiat">Fiat</option>
            <option value="Alfa Romeo">Alfa Romeo</option>
        </optgroup>

        {/* Bikes - India */}
        <optgroup label="🏍️ India (Bike)">
            <option value="Hero MotoCorp">Hero MotoCorp</option>
            <option value="Bajaj Auto">Bajaj Auto</option>
            <option value="TVS Motor">TVS Motor</option>
            <option value="Royal Enfield">Royal Enfield</option>
            <option value="Honda (India)">Honda (India)</option>
            <option value="Suzuki Motorcycle India">Suzuki Motorcycle India</option>
            <option value="Yamaha Motor India">Yamaha Motor India</option>
            <option value="Mahindra Two Wheelers">Mahindra Two Wheelers</option>
            <option value="Ather Energy">Ather Energy</option>
            <option value="Ola Electric">Ola Electric</option>
            <option value="Revolt Motors">Revolt Motors</option>
            <option value="Tork Motors">Tork Motors</option>
        </optgroup>

        {/* Bikes - Japan */}
        <optgroup label="🏍️ Japan (Bike)">
            <option value="Honda">Honda</option>
            <option value="Yamaha">Yamaha</option>
            <option value="Suzuki">Suzuki</option>
            <option value="Kawasaki">Kawasaki</option>
        </optgroup>

        {/* Bikes - USA */}
        <optgroup label="🏍️ USA (Bike)">
            <option value="Harley-Davidson">Harley-Davidson</option>
            <option value="Indian Motorcycle">Indian Motorcycle</option>
            <option value="Zero Motorcycles">Zero Motorcycles</option>
        </optgroup>

        {/* Bikes - Germany */}
        <optgroup label="🏍️ Germany (Bike)">
            <option value="BMW Motorrad">BMW Motorrad</option>
        </optgroup>

        {/* Bikes - UK */}
        <optgroup label="🏍️ UK (Bike)">
            <option value="Triumph Motorcycles">Triumph Motorcycles</option>
            <option value="Norton Motorcycles">Norton Motorcycles</option>
        </optgroup>

        {/* Bikes - Italy */}
        <optgroup label="🏍️ Italy (Bike)">
            <option value="Ducati">Ducati</option>
            <option value="Aprilia">Aprilia</option>
            <option value="MV Agusta">MV Agusta</option>
            <option value="Benelli">Benelli</option>
        </optgroup>

        {/* Bikes - Austria */}
        <optgroup label="🏍️ Austria (Bike)">
            <option value="KTM">KTM</option>
            <option value="Husqvarna">Husqvarna</option>
        </optgroup>
    </select>

    {errors.brand && <small className="text-danger float-right">{errors.brand}</small>}
</div>

                                </div>

                                <div className="form-group form-row">
                                    <label className="col-sm-4 form-control-label">Photo</label>
                                    <div className="col-sm-8">
                                        <input type="file" required name="photo" value={product.photo} onChange={handleInputImage} className="form-control-file" />
                                    </div>
                                </div>

                                <button className="btn btn-primary float-right">Save Product</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default AddProduct;
