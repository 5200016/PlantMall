import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { SysRole } from 'app/shared/model/sys-role.model';
import { SysRoleService } from './sys-role.service';
import { SysRoleComponent } from './sys-role.component';
import { SysRoleDetailComponent } from './sys-role-detail.component';
import { SysRoleUpdateComponent } from './sys-role-update.component';
import { SysRoleDeletePopupComponent } from './sys-role-delete-dialog.component';
import { ISysRole } from 'app/shared/model/sys-role.model';

@Injectable({ providedIn: 'root' })
export class SysRoleResolve implements Resolve<ISysRole> {
    constructor(private service: SysRoleService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<SysRole> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<SysRole>) => response.ok),
                map((sysRole: HttpResponse<SysRole>) => sysRole.body)
            );
        }
        return of(new SysRole());
    }
}

export const sysRoleRoute: Routes = [
    {
        path: 'sys-role',
        component: SysRoleComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysRole.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-role/:id/view',
        component: SysRoleDetailComponent,
        resolve: {
            sysRole: SysRoleResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysRole.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-role/new',
        component: SysRoleUpdateComponent,
        resolve: {
            sysRole: SysRoleResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysRole.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-role/:id/edit',
        component: SysRoleUpdateComponent,
        resolve: {
            sysRole: SysRoleResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysRole.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const sysRolePopupRoute: Routes = [
    {
        path: 'sys-role/:id/delete',
        component: SysRoleDeletePopupComponent,
        resolve: {
            sysRole: SysRoleResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysRole.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
