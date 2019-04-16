/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { PlantMallTestModule } from '../../../test.module';
import { SysRoleDetailComponent } from 'app/entities/sys-role/sys-role-detail.component';
import { SysRole } from 'app/shared/model/sys-role.model';

describe('Component Tests', () => {
    describe('SysRole Management Detail Component', () => {
        let comp: SysRoleDetailComponent;
        let fixture: ComponentFixture<SysRoleDetailComponent>;
        const route = ({ data: of({ sysRole: new SysRole(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysRoleDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(SysRoleDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SysRoleDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.sysRole).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
