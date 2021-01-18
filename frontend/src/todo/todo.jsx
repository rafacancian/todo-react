import React, {Component} from 'react'
import axios from 'axios'

import PageHeader from '../template/pageHeader'
import TodoForm from './todoForm'
import TodoList from './todoList'

const URL = 'http://localhost:8081/todolist/task'

export default class todo extends Component {

    constructor(props){
        super(props)

        this.state = {description: '', list: []}

        this.handleChange = this.handleChange.bind(this)
        this.handleAdd = this.handleAdd.bind(this)
        this.handleSearch = this.handleSearch.bind(this)
        this.handleClear = this.handleClear.bind(this)

        this.handleMarkAsDone = this.handleMarkAsDone.bind(this)
        this.handleMarkAsPending = this.handleMarkAsPending.bind(this)
        this.handleRemove = this.handleRemove.bind(this)

        this.refresh()
    }

    refresh(description = ''){
        debugger
        const filter = description ? `/search?description=${description}` : ''
        axios.get(`${URL}`+ filter)
            .then(resp => {
                debugger
                this.setState({...this.state, description: '', list:resp.data.tasks})
            })
    }

    handleSearch() {
      this.refresh(this.state.description)
    }

    handleAdd() {
        axios.post(URL, {  "description": this.state.description, "done":false, "username": "rafael" })
            .then(resp => {
                debugger
                this.refresh()
            })

    }

    handleChange(e) {
        this.setState({...this.state , description: e.target.value})
    }

    handleMarkAsDone() {
    }

    handleMarkAsPending() {

    }
    handleRemove(id) {
        axios.delete(`${URL}/${id}`)
            .then(resp => this.refresh())
    }

    handleClear() {
        this.refresh()
    }

    render(){
        return (
            <div>
               <PageHeader name='Task' small='Form'></PageHeader>
               <TodoForm
                    description={this.state.description}
                    handleChange={this.handleChange}
                    handleAdd={this.handleAdd}
                    handleSearch={this.handleSearch}
                    handleClear={this.handleClear} />
               <TodoList
                   list={this.state.list}
                   handleMarkAsDone={this.handleMarkAsDone}
                   handleMarkAsPending={this.handleMarkAsPending}
                   handleRemove={this.handleRemove} />
            </div>
        )
    }
}