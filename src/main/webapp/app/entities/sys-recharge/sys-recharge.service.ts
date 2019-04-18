import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISysRecharge } from 'app/shared/model/sys-recharge.model';

type EntityResponseType = HttpResponse<ISysRecharge>;
type EntityArrayResponseType = HttpResponse<ISysRecharge[]>;

@Injectable({ providedIn: 'root' })
export class SysRechargeService {
    public resourceUrl = SERVER_API_URL + 'api/sys-recharges';

    constructor(protected http: HttpClient) {}

    create(sysRecharge: ISysRecharge): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(sysRecharge);
        return this.http
            .post<ISysRecharge>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(sysRecharge: ISysRecharge): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(sysRecharge);
        return this.http
            .put<ISysRecharge>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ISysRecharge>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ISysRecharge[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(sysRecharge: ISysRecharge): ISysRecharge {
        const copy: ISysRecharge = Object.assign({}, sysRecharge, {
            createTime: sysRecharge.createTime != null && sysRecharge.createTime.isValid() ? sysRecharge.createTime.toJSON() : null,
            updateTime: sysRecharge.updateTime != null && sysRecharge.updateTime.isValid() ? sysRecharge.updateTime.toJSON() : null
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
            res.body.forEach((sysRecharge: ISysRecharge) => {
                sysRecharge.createTime = sysRecharge.createTime != null ? moment(sysRecharge.createTime) : null;
                sysRecharge.updateTime = sysRecharge.updateTime != null ? moment(sysRecharge.updateTime) : null;
            });
        }
        return res;
    }
}
