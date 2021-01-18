import React, {Component} from 'react'
import PageHeader from '../template/pageHeader'
import TodoForm from './todoForm'
import TodoList from './todoList'

export default class todo extends Component {

    constructor(props){
        super(props)
        this.handleAdd = this.handleAdd.bind(this)
        this.handleChange = this.handleChange.bind(this)
        this.handleMarkAsDone = this.handleMarkAsDone.bind(this)
        this.handleMarkAsPending = this.handleMarkAsPending.bind(this)
        this.handleRemove = this.handleRemove.bind(this)

        this.state = {
            description: '',
            list: [
                 { "_id":"1", "description":"Description about the task 01", "done":true},
                 { "_id":"2", "description":"Description about the task 02", "done":true},
                 { "_id":"3", "description":"Description about the task 03", "done":true},
                 { "_id":"4", "description":"Description about the task 04", "done":false},
                 { "_id":"5", "description":"Description about the task 05", "done":false},
                 { "_id":"6", "description":"Description about the task 06", "done":false},
                 { "_id":"7", "description":"Description about the task 07", "done":false},
                 { "_id":"8", "description":"Description about the task 08", "done":false}
            ]
        }
    }

    refresh(){
        this.setState({...this.state, description: ''})
    }

    handleAdd() {
        console.log(this)
        this.setState({...this.state, list: [{"_id":"9", "description": description, "done": false}] })
        this.refresh()
    }

    handleChange(e) {
        console.log(this)
        this.setState({...this.state , description: e.target.value})
    }

    handleMarkAsDone() {
        console.log(this)
    }

    handleMarkAsPending() {
        console.log(this)
    }
    handleRemove() {
        console.log(this)
    }

    render(){
        return (
            <div>
               <PageHeader name='Task' small='Form'></PageHeader>
               <TodoForm
                    description={this.state.description}
                    handleAdd={this.handleAdd}
                    handleChange={this.handleChange} />
               <TodoList list={this.state.list}/>
            </div>
        )
    }
}