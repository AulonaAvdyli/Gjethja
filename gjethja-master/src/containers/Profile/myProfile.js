import React from "react";
import {ProfileTabs} from "../../utils/constants";
import {connect} from "react-redux";
import {editProfile, getUser, uploadProfilePicture} from "../../actions/user";
import Post from "../../components/Post/Post";
import {deletePost, editPost, getMyPosts, getPosts} from "../../actions/posts";
import Header from "../../components/Header/Header";
import Stripe from "../../components/Stripe/Stripe";
import {loadStripe} from "@stripe/stripe-js";
import {Elements} from "@stripe/react-stripe-js";
import Modal from "react-modal";

const stripePromise = loadStripe('pk_test_51H1teeE9l7621wtln7yA1DXyqVAQ4Ld6FJwB2iLYNZKtmluZEZ93jeg2ycwuKRGOj71C7awnuQBN5qDYrDkodgp100Xdajq8Lw');

class myProfile extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            user: {
                firstName: "",
                lastName: "",
                bio: "",
                city: "",
                address: "",
                phoneNumber: "",
                image: null
            },
            post: {
                title: "",
                description: "",
                status: ""
            },
            isModalOpen: false,
            currentTab: ProfileTabs.Profile,
            modalContent: ""
        }
    }

    componentDidMount() {
        this.props.getUser().then(res => {
            this.setState({ user: res.data });
        })
        this.props.getMyPosts();
    }

    handleSubmit = (event) => {
        this.props.editProfile(this.state.user).then(res => {
            if(this.state.user.image) {
                this.props.uploadProfilePicture(this.state.user.image).then(res => {
                    this.props.getUser().then(res => {
                        this.setState({ user: res.data });
                    })
                })
            } else {
                this.props.getUser().then(res => {
                    this.setState({ user: res.data });
                })
            }
        })
    }

    handleEditPost = (event) => {
        this.props.editPost(this.state.post, this.state.modalId).then(res => {
            this.props.getPosts().then(res => {
                this.setState({ isModalOpen: false });
            })
        })
    }

    handleDelete = (id) => {
        this.props.deletePost(id).then(res => {
            this.props.getMyPosts();
        })
    }

    setSelectedJobs = (jobs) => {
        this.setState({ jobs: jobs })
    }

    switchTab = (tab) => {
        this.setState({ currentTab: tab });
    }

    renderProfile = () => (
        <div className="row pb-5">
            <div className="col-6 mx-auto register-form-container">
                <form className="register-form">
                    <div className="register-title"> My Profile!</div>

                    <div className="row">
                        <div className="col-4 mx-auto">
                            <img src={"data:image/png;base64," + this.state.user.profilePicture} alt="Logo" style={{ maxWidth: "200px", maxHeight: "200px" }} />
                        </div>
                    </div>

                    <div className="form-row">
                        <div className="col">
                            <div className="form-group">
                                <label htmlFor="name-input">Foto e profilit: </label>
                                <input type="file"
                                       id="name-input"
                                       className="form-control p-1"
                                       placeholder="Image"
                                       onChange={(e) => {
                                           this.setState({
                                               user: {
                                                   ...this.state.user,
                                                   image: e.target.files && e.target.files[0] ? e.target.files[0] : null
                                               }
                                           })
                                       }}/>
                                <div className="error-style">{this.state.firstNameError}</div>
                            </div>
                        </div>
                    </div>

                    <div className="form-row">
                        <div className="col">
                            <div className="form-group">
                                <label htmlFor="name-input">Emri: </label>
                                <input type="text"
                                       id="name-input"
                                       className="form-control"
                                       placeholder="Emri"
                                       value={this.state.user.firstName}
                                       onChange={(e) => {
                                           this.setState({
                                               user: {
                                                   ...this.state.user,
                                                   firstName: e.target.value
                                               }
                                           })
                                       }}/>
                                <div className="error-style">{this.state.firstNameError}</div>
                            </div>
                        </div>
                        <div className="col">
                            <div className="form-group">
                                <label htmlFor="lastname-input">Mbiemri: </label>
                                <input type="text"
                                       id="lastname-input"
                                       className="form-control"
                                       placeholder="Mbiemri"
                                       value={this.state.user.lastName}
                                       onChange={(e) => {
                                           this.setState({
                                               user: {
                                                   ...this.state.user,
                                                   lastName: e.target.value
                                               }
                                           })
                                       }}/>
                                <div className="error-style">{this.state.lastNameError}</div>
                            </div>
                        </div>
                    </div>

                    <div className="form-group">
                        <label htmlFor="phone">Numri i telefonit:</label>
                        <input type="text"
                               id="phone-input"
                               className="form-control"
                               placeholder="Numri i telefonit"
                               value={this.state.user.phoneNumber}
                               onChange={(e) => {
                                   this.setState({
                                       user: {
                                           ...this.state.user,
                                           phoneNumber: e.target.value
                                       }
                                   })
                               }}/>
                        <div className="error-style">{this.state.phoneNumberError}</div>
                    </div>

                    <div className="form-row">
                        <div className="col">
                            <div className="form-group">
                                <label htmlFor="city-input">Qyteti: </label>
                                <select
                                    id="city-input"
                                    className="form-control"
                                    defaultValue="Zgjedh qytetin"
                                    onChange={(e) => {
                                        this.setState({
                                            user: {
                                                ...this.state.user,
                                                city: e.target.value
                                            }
                                        })
                                    }}>
                                    <option defaultValue>Zgjedh qytetin</option>
                                    <option value="Prishtine">Prishtine</option>
                                    <option value="Peje">Peje</option>
                                    <option value="Prizren">Prizren</option>
                                    <option value="Gjakovë">Gjakove</option>
                                    <option value="Fushe Kosove">Fushe Kosove</option>
                                    <option value="Ferizaj">Ferizaj</option>
                                    <option value="Vushtrri">Vushtrri</option>
                                    <option value="Gjilan">Gjilan</option>
                                    <option value="Mitrovice">Mitrovice</option>
                                </select>
                                <div className="error-style">{this.state.cityError}</div>
                            </div>
                        </div>
                        <div className="col">
                            <div className="form-group">
                                <label htmlFor="address-input">Adresa: </label>
                                <input type="text"
                                       id="address-input"
                                       className="form-control"
                                       value={this.state.user.address}
                                       onChange={(e) => {
                                           this.setState({
                                               user: {
                                                   ...this.state.user,
                                                   address: e.target.value
                                               }
                                           })
                                       }}
                                       placeholder="Adresa"/>
                                <div className="error-style">{this.state.addressError}</div>
                            </div>
                        </div>
                    </div>

                    <div className="form-group">
                        <label htmlFor="bio-input">Biografi e shkurtër: </label>
                        <input type="text"
                               id="bio-input"
                               className="form-control"
                               placeholder="Biografia"
                               value={this.state.user.bio}
                               onChange={(e) => {
                                   this.setState({
                                       user: {
                                           ...this.state.user,
                                           bio: e.target.value
                                       }
                                   })
                               }}/></div>

                    <div className="register-button">
                        <button type="button" onClick={() => this.handleSubmit()}>Edit Profile</button>
                    </div>
                </form>
            </div>
        </div>
    )

    closeModal = () => {
        this.setState({ isModalOpen: false });
    }

    openModal = (content, id) => {
        if(content == 'edit-post') {
            const post = this.props.posts.find(post => post.id == id);
            this.setState({ post: post });
        }
        this.setState({ isModalOpen: true, modalId: id, modalContent: content });
    }

    renderPosts = () => (
        <div className="row">
            <div className="col">
                <div className="posts myProfile">
                    {
                        this.props.posts.map((post, index) => {
                            return <Post
                                id={post.id}
                                title={post.title}
                                status={post.status}
                                description={post.description}
                                handleEdit={() => this.openModal("edit-post", post.id)}
                                handleDelete={() => this.handleDelete(post.id)}
                                handlePay={() => this.openModal("handle-pay", post.id)} />
                        })
                    }
                </div>
            </div>
        </div>
    )

    renderModalContent = () => {
        if(this.state.modalContent == "edit-post") {
            return (
                <form className="create-post-form">
                    <div className="form-group">
                        <label htmlFor="title-input">Titulli: </label>
                        <input type="text"
                               id="name-input"
                               className="form-control"
                               placeholder="Titulli"
                               value={this.state.post.title}
                               onChange={(e) => {
                                   this.setState({
                                       post: {
                                           ...this.state.post,
                                           title: e.target.value
                                       }
                                   })
                               }}/></div>

                    <div className="form-group">
                        <label htmlFor="name-input">Përshkrimi: </label>
                        <input type="text"
                               id="name-input"
                               className="form-control"
                               placeholder="Përshkrimi"
                               value={this.state.post.description}
                               onChange={(e) => {
                                   this.setState({
                                       post: {
                                           ...this.state.post,
                                           descrtiption: e.target.value
                                       }
                                   })
                               }}/></div>

                    <div className="form-group">
                        <label htmlFor="status-input">Statusi: </label>
                        <select
                            id="status-input"
                            className="form-control"
                            defaultValue="Select status"
                            onChange={(e) => {
                                this.setState({
                                    post: {
                                        ...this.state.post,
                                        status: e.target.value
                                    }
                                })
                            }}>
                            <option defaultValue>Zgjedh statusin</option>
                            <option value="open" id="opened" >Hapur</option>
                            <option value="closed" id="closed" >Mbyllur</option>
                        </select>
                    </div>

                    <button type="button" id="submit-button" onClick={() => this.handleEditPost()}>Posto</button>
                </form>
            )
        } else {
            return <Elements stripe={stripePromise}>
                    <Stripe id={this.state.modalId} onPaymentFinish={() => this.closeModal()} />
                </Elements>
        }
    }

    render() {
        const { currentTab, isModalOpen, modalId } = this.state;

        return (
            <>
                <Header/>
                <div className="container-fluid register-container" >
                    <div className="row">
                        <div className="col">
                            <div className="navigation">
                                <div className="navigation-link">
                                    <button
                                        onClick={() => this.switchTab(ProfileTabs.Profile)}>
                                        Profili
                                    </button>
                                </div>
                                <div className="navigation-link">
                                    <button type="button" id="posts-button"
                                        onClick={() => this.switchTab(ProfileTabs.Posts)}>
                                        Postimet
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>

                    <Modal
                        isOpen={isModalOpen}
                        onRequestClose={this.closeModal}
                        contentLabel="Payment Modal"
                        className="custom-modal"
                        overlayClassName="custom-modal-overlay">


                        { this.renderModalContent() }

                    </Modal>

                    { currentTab == ProfileTabs.Profile && this.renderProfile() }
                    { currentTab == ProfileTabs.Posts && this.renderPosts() }

                </div>
            </>
        )
    }
}

const mapStateToProps = state => {
    return {
        user: state.user,
        posts: state.posts,
    };
}

const mapDispatchToProps = dispatch => ({
    getUser: data => dispatch(getUser()),
    getPosts: data => dispatch(getPosts()),
    getMyPosts: data => dispatch(getMyPosts()),
    editPost: (data, id) => dispatch(editPost(data, id)),
    deletePost: data => dispatch(deletePost(data)),
    editProfile: (data) => dispatch(editProfile(data)),
    uploadProfilePicture: (data) => dispatch(uploadProfilePicture(data)),
})

export default connect(mapStateToProps, mapDispatchToProps)(myProfile);