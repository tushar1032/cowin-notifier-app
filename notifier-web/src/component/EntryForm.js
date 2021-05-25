import React from 'react'
import './css/EntryForm.css';

export class EntryForm extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            email: "",
            pincode_prefix: "",
            state_name: "",
            district_name: "",
            age_group: "18",
            vaccine: "COVISHIELD"
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(e) {
        const {name, value} = e.target;
        this.setState(() => ({
            [name]: value
        }))
    }

    async handleSubmit() {
        let entry = this.prepareEntryData();
        const options = this.prepareHeader(entry);
        this.handleResponse(options, '/add');
    }

    handleResponse(options, url) {
        fetch(url, options)
            .then((response) => {
                const status = response.status;
                if (status === 200) {
                    response.json().then((data) => {
                        alert(data.message)
                    });
                } else if (status === 422) {
                    response.json().then((data) => {
                        alert(data.message)
                    })
                } else if (status === 500) {
                    this.setState({isValidUser: false});
                    alert("Internal Server Error!")
                } else {
                    this.setState({isValidUser: false});
                    alert("Something Went wrong! Please try again.")
                }
            });
    }

    prepareHeader(entry) {
        const headers = new Headers();
        headers.append('Content-type', 'application/json');
        const options = {
            method: 'POST',
            headers,
            body: JSON.stringify(entry)
        };
        console.log(JSON.stringify(entry))
        return options;
    }

    prepareEntryData() {
        let location = {
            district: this.state.district_name,
            state: this.state.state_name
        }
        let entry = {
            age_group: this.state.age_group,
            email: this.state.email,
            location: location,
            pincodeprefix: this.state.pincode_prefix,
            vaccine: this.state.vaccine
        };
        return entry;
    }

    render() {
        return (
                <form className="container entry_form">
                    <center><h1><span className="badge badge-primary">Cowin Notification System</span></h1></center>
                    <div className="form-group">
                        <label>Email</label>
                        <input
                            aria-describedby="emailHelp"
                            className="form-control"
                            type="text"
                            required
                            value={this.state.email}
                            name="email"
                            placeholder="Enter email"
                            onChange={this.handleChange}
                            id="emailInput"
                        />
                        <small id="emailHelp" className="form-text text-muted">We'll never share your email with anyone
                            else.</small>
                    </div>
                    <div className="form-group">
                        <label>Pincode Prefix</label>
                        <input
                            className="form-control"
                            type="text"
                            required
                            value={this.state.pincode_prefix}
                            name="pincode_prefix"
                            onChange={this.handleChange}
                            id="pincode"
                            placeholder="Enter the first 3 numbers of your pincode"
                        />
                    </div>
                    <div className="form-group">
                        <label>State</label>
                        <input
                            className="form-control"
                            type="text"
                            required
                            value={this.state.state_name}
                            name="state_name"
                            onChange={this.handleChange}
                            id="stateName"
                            placeholder="Enter State"
                        />
                    </div>
                    <div className="form-group">
                        <label>District</label>
                        <input
                            className="form-control"
                            type="text"
                            required
                            value={this.state.district_name}
                            name="district_name"
                            onChange={this.handleChange}
                            id="districtName"
                            placeholder="Enter District"
                        />
                    </div>
                    <div className="form-group">
                        <div className="form-check-label">
                            <label>Age Group</label>
                        </div>
                        <div className="dropdown">
                            <select className="form-control" name="age_group" value={this.state.age_group}
                                    onChange={this.handleChange}
                                    id="ageGroup">
                                <option value="18">18+</option>
                                <option value="45">45+</option>
                            </select>
                        </div>
                    </div>
                    <div className="form-group">
                        <div className="form-check-label">
                            <label>Vaccine</label>
                        </div>
                        <div className="dropdown">
                            <select
                                name="vaccine"
                                onChange={this.handleChange}
                                value={this.state.vaccine}
                                className="form-control">
                                <option value="COVISHIELD">COVISHIELD</option>
                                <option value="COVAXINE">COVAXINE</option>
                            </select>
                        </div>
                    </div>
                    <div className="button_css">
                        <button type="button" className="btn btn-primary"
                                onClick={this.handleSubmit}>SUBMIT
                        </button>
                    </div>
                </form>
        )
    }
}

