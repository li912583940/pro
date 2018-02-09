import axiose from '@/utils/axiose'

export const requestLogin = params => { return axiose.post('/login', params).then(res => res.data); };