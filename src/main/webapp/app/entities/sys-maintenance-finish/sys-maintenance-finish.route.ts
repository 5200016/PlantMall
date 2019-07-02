import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { SysMaintenanceFinish } from 'app/shared/model/sys-maintenance-finish.model';
import { SysMaintenanceFinishService } from './sys-maintenance-finish.service';
import { SysMaintenanceFinishComponent } from './sys-maintenance-finish.component';
import { SysMaintenanceFinishDetailComponent } from './sys-maintenance-finish-detail.component';
import { SysMaintenanceFinishUpdateComponent } from './sys-maintenance-finish-update.component';
import { SysMaintenanceFinishDeletePopupComponent } from './sys-maintenance-finish-delete-dialog.component';
import { ISysMaintenanceFinish } from 'app/shared/model/sys-maintenance-finish.model';

@Injectable({ providedIn: 'root' })
export class SysMaintenanceFinishResolve implements Resolve<ISysMaintenanceFinish> {
    constructor(private service: SysMaintenanceFinishService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<SysMaintenanceFinish> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<SysMaintenanceFinish>) => response.ok),
                map((sysMaintenanceFinish: HttpResponse<SysMaintenanceFinish>) => sysMaintenanceFinish.body)
            );
        }
        return of(new SysMaintenanceFinish());
    }
}

export const sysMaintenanceFinishRoute: Routes = [
    {
        path: 'sys-maintenance-finish',
        component: SysMaintenanceFinishComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysMaintenanceFinish.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-maintenance-finish/:id/view',
        component: SysMaintenanceFinishDetailComponent,
        resolve: {
            sysMaintenanceFinish: SysMaintenanceFinishResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysMaintenanceFinish.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-maintenance-finish/new',
        component: SysMaintenanceFinishUpdateComponent,
        resolve: {
            sysMaintenanceFinish: SysMaintenanceFinishResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysMaintenanceFinish.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-maintenance-finish/:id/edit',
        component: SysMaintenanceFinishUpdateComponent,
        resolve: {
            sysMaintenanceFinish: SysMaintenanceFinishResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysMaintenanceFinish.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const sysMaintenanceFinishPopupRoute: Routes = [
    {
        path: 'sys-maintenance-finish/:id/delete',
        component: SysMaintenanceFinishDeletePopupComponent,
        resolve: {
            sysMaintenanceFinish: SysMaintenanceFinishResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysMaintenanceFinish.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
