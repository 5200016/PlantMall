import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISysAdmin } from 'app/shared/model/sys-admin.model';

type EntityResponseType = HttpResponse<ISysAdmin>;
type EntityArrayResponseType = HttpResponse<ISysAdmin[]>;

@Injectable({ providedIn: 'root' })
export class SysAdminService {
    public resourceUrl = SERVER_API_URL + 'api/sys-admins';

    constructor(protected http: HttpClient) {}

    create(sysAdmin: ISysAdmin): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(sysAdmin);
        return this.http
            .post<ISysAdmin>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(sysAdmin: ISysAdmin): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(sysAdmin);
        return this.http
            .put<ISysAdmin>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ISysAdmin>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ISysAdmin[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(sysAdmin: ISysAdmin): ISysAdmin {
        const copy: ISysAdmin = Object.assign({}, sysAdmin, {
            createTime: sysAdmin.createTime != null && sysAdmin.createTime.isValid() ? sysAdmin.createTime.toJSON() : null,
            updateTime: sysAdmin.updateTime != null && sysAdmin.updateTime.isValid() ? sysAdmin.updateTime.toJSON() : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.createTime = res.body.createTime != null ? moment(res.body.createTime) : null;
            res.body.updateTime = res.body.updateTime != null ? moment(res.body.updateTime) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((sysAdmin: ISysAdmin) => {
                sysAdmin.createTime = sysAdmin.createTime != null ? moment(sysAdmin.createTime) : null;
                sysAdmin.updateTime = sysAdmin.updateTime != null ? moment(sysAdmin.updateTime) : null;
            });
        }
        return res;
    }
}
