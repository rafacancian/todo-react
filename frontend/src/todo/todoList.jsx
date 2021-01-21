import React from 'react'
import IconButton from '../template/iconButton'

export default props => {

   const renderRows = () => {

           const list = props.list || []

           return list.map(task => (
               <tr key={task.id}>
                   <td>
                       <IconButton style='default' icon='check' hide={task.done} onClick={() => props.handleMarkAsDone(task)}></IconButton>
                       <IconButton style='success' icon='check' hide={!task.done} onClick={() => props.handleMarkAsPending(task)}></IconButton>
                   </td>
                   <td className={task.done ? 'markedAsDone text-success' : ''}>{task.description}</td>
                       <td className={task.done ? 'markedAsDone text-success' : ''}>{task.creation_date}</td>
                       <td className={task.done ? 'markedAsDone text-success' : ''}>{task.due_date}</td>
                   <td>
                       <IconButton style='danger' icon='trash-o' onClick={() => props.handleRemove(task.id)}></IconButton>
                   </td>
               </tr>
           ))
       }

       return (
           <table className='table table-striped'>
               <thead>
                   <tr>
                       <th className='tableActions'></th>
                       <th>DESCRIPTION</th>
                       <th>CREATION DATE</th>
                       <th>DUE DATE</th>
                       <th className='tableActions'></th>
                   </tr>
               </thead>
               <tbody>
                   {renderRows()}
               </tbody>
           </table>
       )
}