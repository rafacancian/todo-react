import React from 'react'
import IconButton from '../template/iconButton'

export default props => {

   const renderRows = () => {

           const list = [
               { "_id":"1", "description":"Description about the task 01", "done":true},
               { "_id":"2", "description":"Description about the task 02", "done":true},
               { "_id":"3", "description":"Description about the task 03", "done":true},
               { "_id":"4", "description":"Description about the task 04", "done":false},
               { "_id":"5", "description":"Description about the task 05", "done":false},
               { "_id":"6", "description":"Description about the task 06", "done":false},
               { "_id":"7", "description":"Description about the task 07", "done":false},
               { "_id":"8", "description":"Description about the task 08", "done":false}]

           return list.map(todo => (
               <tr key={todo._id}>
                   <td className={todo.done ? 'markedAsDone' : ''}>{todo.description}</td>
                   <td>
                       <IconButton style='success' icon='check' hide={todo.done}
                           onClick={() => props.handleMarkAsDone(todo)}></IconButton>
                       <IconButton style='warning' icon='undo' hide={!todo.done}
                           onClick={() => props.handleMarkAsPending(todo)}></IconButton>
                       <IconButton style='danger' icon='trash-o' hide={!todo.done}
                           onClick={() => props.handleRemove(todo)}></IconButton>
                   </td>
               </tr>
           ))
       }

       return (
           <table className='table'>
               <thead>
                   <tr>
                       <th>Descrição</th>
                       <th className='tableActions'>Ações</th>
                   </tr>
               </thead>
               <tbody>
                   {renderRows()}
               </tbody>
           </table>
       )
}