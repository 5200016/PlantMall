import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISysMemberSetting } from 'app/shared/model/sys-member-setting.model';

type EntityResponseType = HttpResponse<ISysMemberSetting>;
type EntityArrayResponseType = HttpResponse<ISysMemberSetting[]>;

@Injectable({ providedIn: 'root' })
export class SysMemberSettingService {
    public resourceUrl = SERVER_API_URL + 'api/sys-member-settings';

    constructor(protected http: HttpClient) {}

    create(sysMemberSetting: ISysMemberSetting): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(sysMemberSetting);
        return this.http
            .post<ISysMemberSetting>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(sysMemberSetting: ISysMemberSetting): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(sysMemberSetting);
        return this.http
            .put<ISysMemberSetting>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ISysMemberSetting>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ISysMemberSetting[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(sysMemberSetting: ISysMemberSetting): ISysMemberSetting {
        const copy: ISysMemberSetting = Object.assign({}, sysMemberSetting, {
            createTime:
                sysMemberSetting.createTime != null && sysMemberSetting.createTime.isValid() ? sysMemberSetting.createTime.toJSON() : null,
            updateTime:
                sysMemberSetting.updateTime != null && sysMemberSetting.updateTime.isValid() ? sysMemberSetting.updateTime.toJSON() : null
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
            res.body.forEach((sysMemberSetting: ISysMemberSetting) => {
                sysMemberSetting.createTime = sysMemberSetting.createTime != null ? moment(sysMemberSetting.createTime) : null;
                sysMemberSetting.updateTime = sysMemberSetting.updateTime != null ? moment(sysMemberSetting.updateTime) : null;
            });
        }
        return res;
    }
}
