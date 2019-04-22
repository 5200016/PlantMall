import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISysPlantLog } from 'app/shared/model/sys-plant-log.model';

type EntityResponseType = HttpResponse<ISysPlantLog>;
type EntityArrayResponseType = HttpResponse<ISysPlantLog[]>;

@Injectable({ providedIn: 'root' })
export class SysPlantLogService {
    public resourceUrl = SERVER_API_URL + 'api/sys-plant-logs';

    constructor(protected http: HttpClient) {}

    create(sysPlantLog: ISysPlantLog): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(sysPlantLog);
        return this.http
            .post<ISysPlantLog>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(sysPlantLog: ISysPlantLog): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(sysPlantLog);
        return this.http
            .put<ISysPlantLog>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ISysPlantLog>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ISysPlantLog[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(sysPlantLog: ISysPlantLog): ISysPlantLog {
        const copy: ISysPlantLog = Object.assign({}, sysPlantLog, {
            createTime: sysPlantLog.createTime != null && sysPlantLog.createTime.isValid() ? sysPlantLog.createTime.toJSON() : null,
            updateTime: sysPlantLog.updateTime != null && sysPlantLog.updateTime.isValid() ? sysPlantLog.updateTime.toJSON() : null
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
            res.body.forEach((sysPlantLog: ISysPlantLog) => {
                sysPlantLog.createTime = sysPlantLog.createTime != null ? moment(sysPlantLog.createTime) : null;
                sysPlantLog.updateTime = sysPlantLog.updateTime != null ? moment(sysPlantLog.updateTime) : null;
            });
        }
        return res;
    }
}
