import React from 'react'
import Grid from '../template/grid'
import IconButton from '../template/iconButton'

export default props => {

      return (
            <div role='form' className='todoForm'>
                <Grid cols='12 9 10'>
                    <input id='description' className='form-control'
                        placeholder='Add Task'
                        value=''></input>
                </Grid>
                <Grid cols='12 3 2'>
                    <IconButton style='primary' icon='plus'></IconButton>
                    <IconButton style='info' icon='search'></IconButton>
                    <IconButton style='default' icon='close'></IconButton>
                </Grid>
            </div>
        )
}