import axiose from '@/utils/axiose'

let base = '/user';

export const getUserOne = params => { return axiose.get(`${base}/findOne`, { params: params }).then(res => res.data); };

export const getUserListPage = params => { return axiose.get(`${base}/findList`, { params: params }).then(res => res.data); };

export const removeUser = params => { return axiose.post(`${base}/delete`, params).then(res => res.data); };

export const batchRemoveUser = params => { return axiose.post(`${base}/delete`, params).then(res => res.data); };

export const editUser = params => { return axiose.post(`${base}/update`, params).then(res => res.data); };

export const addUser = params => { return axiose.post(`${base}/insert`, params).then(res => res.data); };

export const getImplementUserNameList = params => { return axiose.get(`${base}/findNameList`, { params: params }).then(res => res.data); };