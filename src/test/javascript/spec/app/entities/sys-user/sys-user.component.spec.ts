/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { PlantMallTestModule } from '../../../test.module';
import { SysUserComponent } from 'app/entities/sys-user/sys-user.component';
import { SysUserService } from 'app/entities/sys-user/sys-user.service';
import { SysUser } from 'app/shared/model/sys-user.model';

describe('Component Tests', () => {
    describe('SysUser Management Component', () => {
        let comp: SysUserComponent;
        let fixture: ComponentFixture<SysUserComponent>;
        let service: SysUserService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysUserComponent],
                providers: []
            })
                .overrideTemplate(SysUserComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SysUserComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SysUserService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new SysUser(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.sysUsers[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
