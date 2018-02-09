import axiose from '@/utils/axiose'

let base = '/project';

export const getProjectOne = params => { return axiose.get(`${base}/findOne`, { params: params }).then(res => res.data); };

export const getProjectListPage = params => { return axiose.get(`${base}/findList`, { params: params }).then(res => res.data); };

export const removeProject = params => { return axiose.post(`${base}/delete`, params).then(res => res.data); };

export const batchRemoveProject = params => { return axiose.post(`${base}/delete`, params).then(res => res.data); };

export const editProject = params => { return axiose.post(`${base}/update`, params).then(res => res.data); };

export const addProject = params => { return axiose.post(`${base}/insert`, params).then(res => res.data); };

