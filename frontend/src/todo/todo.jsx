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
            .then(resp => this.setState({...this.state, description: '', list:resp.data.tasks}))
    }

    handleSearch() {
      this.refresh(this.state.description)
    }

    handleAdd() {
        const description = this.state.description;
        debugger
        if(description != ''){
            axios.post(URL, {  "description": description, "done":false, "username": "rafael" })
                .then(resp => this.refresh())
        }
    }

    handleChange(e) {
        this.setState({...this.state , description: e.target.value})
    }

    handleMarkAsDone(task) {
        axios.put(URL, {...task, done: true})
            .then(resp => this.refresh())
     }

    handleMarkAsPending(task) {
        axios.put(URL, {...task, done: false})
            .then(resp => this.refresh())

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
               <PageHeader name='Task'></PageHeader>
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